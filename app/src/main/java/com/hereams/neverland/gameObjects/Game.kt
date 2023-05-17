package com.hereams.neverland.gameObjects

import android.content.Context
import android.content.Intent
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.os.Handler
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat.startActivity
import com.hereams.neverland.R
import com.hereams.neverland.activity.Scene
import com.hereams.neverland.constant.Helper
import com.hereams.neverland.constant.TILEMAP
import com.hereams.neverland.gameLoop.thread.GameLoop
import com.hereams.neverland.gameObjects.view.component.*
import com.hereams.neverland.gameObjects.view.component.character.*
import com.hereams.neverland.gameObjects.view.component.enemy.EnemyView
import com.hereams.neverland.gameObjects.view.component.inventory.ItemView
import com.hereams.neverland.gameObjects.view.component.item.SteelSword
import com.hereams.neverland.gameObjects.view.component.map.Tile
import com.hereams.neverland.gameObjects.view.component.map.map_list.TheHallWay
import com.hereams.neverland.gameObjects.view.component.map.TileMap
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.Sprites
import com.hereams.neverland.graphics.SpritesSheet
import kotlin.system.exitProcess

/**
 * This class is used for handling all entities in game to screen such as:
 *
 * Character, Enemy, Tilemap, Game loop, dpad, Game display, Performance, ...
 *
 * Extends: SurfaceView
 *
 * Implement: SurfaceHolder.Callback
 */
class Game(
    context: Context,
    val dpad: DPadView,
    val character: CharacterView,
    val atk_btn: AttackButtonView
) :
    SurfaceView(context), SurfaceHolder.Callback {

    //Entities
    private lateinit var infoBox: InfoBox
    private lateinit var tile_map: TileMap
    private lateinit var obstacle_list: MutableList<Tile>
    lateinit var enemy_list: MutableList<EnemyView>

    //Game loop
    private lateinit var game_loop: GameLoop

    private lateinit var game_display: GameDisplay
    private lateinit var game_over: GameOver

    private var helper = Helper(this)

    //sprite sheets
    private lateinit var tilemap_spritesheet_array: MutableList<SpritesSheet>
    private lateinit var grass_sprite_sheet: SpritesSheet
    private lateinit var lava_sprite_sheet: SpritesSheet

    //maps
    private lateinit var the_hall_way: TheHallWay

    //parameters
    private var dpadPointerId = -1

    init {
        setBackgroundColor(Color.TRANSPARENT)
        setZOrderMediaOverlay(true)
        val surfaceHolder: SurfaceHolder = holder
        surfaceHolder.addCallback(this)

        game_loop = GameLoop(this, surfaceHolder)
        game_over = GameOver(context)

        //init sprite sheets
        tilemap_spritesheet_array = mutableListOf()
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_grass, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_lava, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_water, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_sand, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_stone, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_smooth_stone, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_gravel, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_dirt, null, null, TILEMAP)
        )
        tilemap_spritesheet_array.add(
            SpritesSheet(this.context, R.drawable.tilemap_ice, null, null, TILEMAP)
        )

        infoBox = InfoBox(this.context, character)

        //init maps, tilemap with enemies inside
        the_hall_way = TheHallWay(this.context, character)
        tile_map = TileMap(tilemap_spritesheet_array, the_hall_way)
        obstacle_list = tile_map.getObstacles()
        enemy_list = tile_map.getEnemy()

        // Initialize display and center it around the player

        val displayMetrics = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(
            displayMetrics
        )

        game_display =
            GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, character)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //the latest drawn  entity will appear first
        tile_map.draw(canvas, game_display)
        dpad.draw(canvas)
        for (i in 0 until enemy_list.size)
            enemy_list[i].draw(canvas, game_display)
//
        character.draw(canvas, game_display)
        infoBox.draw(canvas)

        if(character.model.getPlayerHp() == 0) {
            game_over.draw(canvas)
        }

    }

    fun update(fps: Float) {

        //Update game state

        dpad.update()
        for (i in 0 until enemy_list.size)
            enemy_list[i].update(fps, obstacle_list)

        character.update(fps, obstacle_list)
        game_display.update()

        var size = enemy_list.size

        if (character.readyToAttack() && atk_btn.getIsPressed()) {
            var i = 0
            while (i < size) {
                if (Circle.isColliding(character, enemy_list[i])) {
                    character.attack(enemy_list[i])

                    //enemy died -> drop item, gain exp, gain gold, remove
                    if (enemy_list[i].model.getEnemyHp().toInt() <= 0) {
                        enemy_list[i].drop(character.inventory)
                        character.gainGoldFromEnemy(enemy_list[i].goldGain())
                        character.gainEXPFromEnemy(enemy_list[i].expGain())
                        tile_map.removeEnemy(i)
                        enemy_list = tile_map.getEnemy()
                        --size
                        --i
                    }

                    character.setReadyToAttack(false)
                    character.setIsAttacking(true)
                }
                ++i
            }

            character.setReadyToAttack(false)
        }

        //check for collision between player and enemies
        for (i in 0 until size) {

            //character is in range of enemy[i] attack
            if (Circle.isColliding(character, enemy_list[i]) && enemy_list[i].readyToAttack()) {
                enemy_list[i].attack(character)
                enemy_list[i].setReadyToAttack(false)
                enemy_list[i].setIsAttacking(true)
            } else if (!Circle.isColliding(
                    character,
                    enemy_list[i]
                ) && !enemy_list[i].readyToAttack()
            ) {
                enemy_list[i].move()
            }

        }

        if(enemy_list.size == 0) {
            tile_map.reviveAll()
            enemy_list = tile_map.getEnemy()
        }

        if(character.model.getPlayerHp() == 0) {
            Thread {
                Thread.sleep(5000)

            }.start()
        }

    }

    /**
     * function for init threads
     */
    override fun surfaceCreated(holder: SurfaceHolder) {
        if (game_loop.state.equals(Thread.State.TERMINATED)) {
            val surfaceHolder = getHolder()
            surfaceHolder.addCallback(this)
            game_loop = GameLoop(this, surfaceHolder)
        }
        game_loop.startLoop()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {

    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {

    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touched_position: PointF = PointF(event.x, event.y)
        // Handle user input touch event actions
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                //touched in dpad range

                if (!dpad.getIsPressed() && dpad.isPressed(touched_position)) {
                    // dpad is pressed in this event -> setIsPressed(true) and store pointer id
                    dpadPointerId = event.getPointerId(event.actionIndex)
                    dpad.setIsPressed(true)
                    dpad.setDpadActuator(touched_position)
                }

                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (dpad.getIsPressed()) {
                    // dpad was pressed previously and is now moved
                    dpad.setDpadActuator(touched_position)
                }
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                if (dpadPointerId == event.getPointerId(event.actionIndex)) {
                    // dpad pointer was let go off -> setIsPressed(false) and resetActuator()
                    dpad.setIsPressed(false)
                    dpad.resetActuator()
                    dpadPointerId = -1
                }
                return true
            }
        }

        return super.onTouchEvent(event)

    }

}