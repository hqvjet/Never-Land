package com.hereams.neverland.gameObjects

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.view.WindowManager
import com.hereams.neverland.R
import com.hereams.neverland.constant.CIRCLE_RADIUS
import com.hereams.neverland.constant.Helper
import com.hereams.neverland.constant.TILEMAP
import com.hereams.neverland.gameLoop.thread.GameLoop
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState
import com.hereams.neverland.gameObjects.view.component.*
import com.hereams.neverland.gameObjects.view.map.TheHallWay
import com.hereams.neverland.gameObjects.view.map.TileMap
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.SpritesSheet

/**
 * This class is used for handling all entities in game to screen such as:
 *
 * Character, Enemy, Tilemap, Game loop, dpad, Game display, Performance, ...
 *
 * Extends: SurfaceView
 *
 * Implement: SurfaceHolder.Callback
 */
class Game(context: Context, val dpad: DPadView, val character: CharacterView, val atk_btn: AttackButtonView) :
    SurfaceView(context), SurfaceHolder.Callback {

    //Entities
    private lateinit var infoBox: InfoBox
    private lateinit var tile_map: TileMap
    lateinit var enemy_list: Array<EnemyView>

    //Game loop
    private lateinit var game_loop: GameLoop

    private lateinit var game_display: GameDisplay

    private var helper = Helper(this)

    //sprite sheets
    private lateinit var earth_sprite_sheet: SpritesSheet

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

        //init sprite sheets
        earth_sprite_sheet =
            SpritesSheet(this.context, R.drawable.sprite_sheet, null, null, TILEMAP)

        infoBox = InfoBox(this.context, character)

        //init maps, tilemap with enemies inside
        the_hall_way = TheHallWay(this.context, character)
        tile_map = TileMap(earth_sprite_sheet, the_hall_way)
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

        for (i in enemy_list.indices)
            enemy_list[i].draw(canvas, game_display)

        character.draw(canvas, game_display)
        infoBox.draw(canvas)

    }

    fun update(fps: Float) {

        //Update game state

        dpad.update()
        for (i in enemy_list.indices)
            enemy_list[i].update(fps)

        character.update(fps)
        infoBox.update()
        game_display.update()

        //check for collision between player and enemies
        for (i in enemy_list.indices) {

            //character is in range of enemy[i] attack
            if (Circle.isColliding(character, enemy_list[i]) && enemy_list[i].readyToAttack()) {
                    enemy_list[i].attack(character)
                    enemy_list[i].setReadyToAttack(false)
                    enemy_list[i].setIsAttacking(true)
            }

            else if(!Circle.isColliding(character, enemy_list[i]) && !enemy_list[i].readyToAttack()) {
                enemy_list[i].move()
            }

        }

        if(character.readyToAttack() && atk_btn.getIsPressed()) {

            for (i in enemy_list.indices) {
                if (Circle.isColliding(character, enemy_list[i])) {
                    character.attack(enemy_list[i])
                    character.setReadyToAttack(false)
                    character.setIsAttacking(true)
                }
            }

            character.setReadyToAttack(false)
        }

    }

    fun pause() {
        game_loop.stopLoop()
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