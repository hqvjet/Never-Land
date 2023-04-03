package com.hereams.neverland.gameObjects

import android.app.Activity
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.util.DisplayMetrics
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.constant.Helper
import com.hereams.neverland.constant.SPRITES_SIZE
import com.hereams.neverland.gameLoop.GameLoop
import com.hereams.neverland.gameObjects.view.component.CharacterView
import com.hereams.neverland.gameObjects.view.component.DPadView
import com.hereams.neverland.gameObjects.view.component.InfoBox
import com.hereams.neverland.graphics.GameDisplay


/**
 * This class is used for handling all entities in game to screen such as:
 *
 * Character, Enemy, Tilemap, Game loop, dpad, Game display, Performance, ...
 *
 * Extends: SurfaceView
 *
 * Implement: SurfaceHolder.Callback
 */
class Game(context: Context) : SurfaceView(context), SurfaceHolder.Callback {

    //Entities
    private lateinit var character: CharacterView
    private lateinit var dpad: DPadView
    private lateinit var infoBox: InfoBox

    //Game loop
    private lateinit var game_loop: GameLoop

    private lateinit var game_display: GameDisplay

    private var helper = Helper(this)

    //parameters
    private var dpadPointerId = 0

    init {
        setBackgroundColor(Color.TRANSPARENT)
        val surfaceHolder: SurfaceHolder = holder
        surfaceHolder.addCallback(this)

        game_loop = GameLoop(this, surfaceHolder)

        dpad = DPadView(
            PointF(
                helper.toDP(500f),
                helper.toDP(500f)
            ),
            helper.toDP(80f),
            helper.toDP(200f)
        )

        character = CharacterView(
            context,
            PointF(
                helper.toDP((width / 2).toFloat()),
                helper.toDP((height / 2).toFloat())
            ),
            helper.toDP(SPRITES_SIZE.toFloat()),
            dpad
        )

        infoBox = InfoBox(this)

        // Initialize display and center it around the player
        val displayMetrics = DisplayMetrics()
        (getContext() as Activity).windowManager.defaultDisplay.getMetrics(displayMetrics)
        game_display =
            GameDisplay(displayMetrics.widthPixels, displayMetrics.heightPixels, character)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

//        TODO("implement all own algorithm about movement to all entity's update function")
        dpad.draw(canvas)
        character.draw(canvas, game_display)
        infoBox.draw(canvas)
    }

    fun update() {

        //Update game state

        dpad.update()
        character.update()
        infoBox.update()

    }

    fun pause() {
        game_loop.stopLoop()
    }

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

    override fun onTouchEvent(event: MotionEvent?): Boolean {

        // Handle user input touch event actions
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN, MotionEvent.ACTION_POINTER_DOWN -> {
                if (dpad.isPressed(PointF(event.x, event.y))) {
                    // dpad is pressed in this event -> setIsPressed(true) and store pointer id
                    dpadPointerId = event.getPointerId(event.actionIndex)
                    dpad.setIsPressed(true)

                    println("pressed")
                } else {
                    println("outside")
                }
                return true
            }
            MotionEvent.ACTION_MOVE -> {
                if (dpad.getIsPressed() && event.getPointerId(event.actionIndex) === dpadPointerId) {
                    // dpad was pressed previously and is now moved
                    dpad.setDpadActuator(PointF(event.x, event.y))
                    println("moving")
                }
                return true
            }
            MotionEvent.ACTION_UP, MotionEvent.ACTION_POINTER_UP -> {
                if (event.getPointerId(event.actionIndex) === dpadPointerId) {
                    // dpad pointer was let go off -> setIsPressed(false) and resetActuator()
                    dpad.setIsPressed(false)
                    dpad.resetActuator()
                    println("lifted")
                }
                return true
            }
        }

        return super.onTouchEvent(event)
    }

}