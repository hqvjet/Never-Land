package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.constant.Helper
import com.hereams.neverland.gameLoop.controller.thread.DPadThread
import com.hereams.neverland.gameLoop.service.DPadService

class DPadView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {

    private val paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private lateinit var character_view: CharacterView
    var service: DPadService = DPadService(this)
    private val helper: Helper = Helper(this)
    var position: PointF = PointF(helper.toDP(250f), helper.toDP(250f))
    val center: Float = helper.toDP(250f)
    var outerRadius = helper.toDP(200f)
    private var innerRadius = helper.toDP(80f)
    var surfaceHolder: SurfaceHolder = holder
    lateinit var canvas: Canvas

    var thread: DPadThread

    init {
        // get sf holder object
        setBackgroundColor(Color.TRANSPARENT)
        thread = DPadThread(service)

        surfaceHolder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(holder: SurfaceHolder) {
                thread.turnOn()
            }

            override fun surfaceChanged(
                holder: SurfaceHolder,
                format: Int,
                width: Int,
                height: Int
            ) {

            }

            override fun surfaceDestroyed(holder: SurfaceHolder) {
                var retry: Boolean = true
                while (retry) {
                    try {
                        thread.turnOff()
                        retry = false
                    } catch (e: InterruptedException) {
                    }
                }
            }

        })

    }

    override fun onDraw(canvas: Canvas?) {
        canvas?.drawColor(Color.TRANSPARENT)
        canvas?.drawCircle(
            center,
            center,
            outerRadius,
            paint
        )      // joystick border
        canvas?.drawCircle(
            position.x,
            position.y,
            innerRadius, paint
        )     // joystick circle

        super.onDraw(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val rawX = event.rawX // raw x position in pixels
                val rawY = event.rawY // raw y position in pixels

                val location = IntArray(2)
                getLocationOnScreen(location)

                val x = rawX - location[0] // adjust x to be relative to view
                val y = rawY - location[1] // adjust y to be relative to view

                service.update(helper.toDP(x), helper.toDP(y))

                service.holding = true
            }

            MotionEvent.ACTION_MOVE -> {

                //eliminate previous thread
                var retry: Boolean = true
                service.is_thread_running = false
                while (retry) {
                    try {
                        service.holding = false
                        service.action_handler.join()
                        retry = false
                    } catch (e: InterruptedException) {
                    }
                }

                val rawX = event.rawX // raw x position in pixels
                val rawY = event.rawY // raw y position in pixels

                val location = IntArray(2)
                getLocationOnScreen(location)

                val x = rawX - location[0] // adjust x to be relative to view
                val y = rawY - location[1] // adjust y to be relative to view

                service.update(helper.toDP(x), helper.toDP(y))
                service.holding = true
            }

            MotionEvent.ACTION_UP -> {

                var retry: Boolean = true
                service.is_thread_running = false
                while (retry) {
                    try {
                        service.holding = false
                        service.action_handler.join()
                        retry = false
                    } catch (e: InterruptedException) {
                    }
                }

                character_view.service.update(0f, 0f)
                service.update(center, center)

            }
        }
        return true
    }

    fun addCharacter(characterView: CharacterView) {
        character_view = characterView
        service.addCharacter(characterView)
    }

}