package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.view.*
import android.widget.FrameLayout
import com.hereams.neverland.gameLoop.thread.GameLoop
import kotlin.math.pow
import kotlin.math.sqrt

class AttackButtonView(
    context: Context,
    val view_position: PointF,
    val size: PointF,
    val center_position: PointF,
    val radius: Float, val character: CharacterView
) : SurfaceView(context), SurfaceHolder.Callback {

    private val paint = Paint().apply {
        color = Color.BLUE
        style = Paint.Style.FILL_AND_STROKE
    }

    private var is_pressed: Boolean = false

    private lateinit var surfaceHolder: SurfaceHolder
    private lateinit var canvas: Canvas

    //Positions
    var circle_position: PointF = PointF(center_position.x, center_position.y)

    init {

        translationX = view_position.x
        translationY = view_position.y

        val layoutParam = FrameLayout.LayoutParams(size.x.toInt(), size.y.toInt())

        layoutParams = layoutParam

        setBackgroundColor(Color.TRANSPARENT)
        surfaceHolder = holder
        surfaceHolder.addCallback(this)

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        canvas?.drawCircle(
            circle_position.x,
            circle_position.y,
            radius,
            paint
        )
    }

    override fun onTouchEvent(event: MotionEvent): Boolean {
        val touched_position: PointF = PointF(event.x, event.y)
        // Handle user input touch event actions
        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> {
                //touched in attack button range
                if (!getIsPressed() && isPressed(touched_position)) {
                    if (character.readyToAttack() && !character.isAttacking()) {
                        character.setIsAttacking(true)
                    }
                    setIsPressed(true)
                    changeBackgroundColor(Color.RED)

                    postInvalidate()
                    drawButton()
                }

                return true
            }
            MotionEvent.ACTION_UP -> {
                if (getIsPressed()) {
                    setIsPressed(false)
                    changeBackgroundColor(Color.BLUE)
                    postInvalidate()
                    drawButton()
                }
                return true
            }
        }

        return super.onTouchEvent(event)

    }

    fun isPressed(touched_position: PointF): Boolean {

        return (circle_position.x - touched_position.x).pow(2) + (circle_position.y - touched_position.y).pow(
            2
        ) <= radius.pow(2)

    }

    fun changeBackgroundColor(color: Int) {
        paint.color = color
    }

    fun getIsPressed(): Boolean {
        return is_pressed
    }

    fun setIsPressed(isPressed: Boolean) {
        is_pressed = isPressed
    }

    fun drawButton() {
        canvas = surfaceHolder.lockCanvas()
        draw(canvas)
        surfaceHolder.unlockCanvasAndPost(canvas)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        surfaceHolder = getHolder()
        surfaceHolder.addCallback(this)

        drawButton()
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
//        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
//        TODO("Not yet implemented")
    }

}