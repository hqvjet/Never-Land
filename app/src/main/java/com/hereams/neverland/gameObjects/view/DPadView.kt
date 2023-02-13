package com.hereams.neverland.gameObjects.view

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.graphics.PointF

class DPadView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    private val paint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.STROKE
        strokeWidth = 4f
    }

    private var center = PointF(250f, 500f)
    private var outerRadius = 200f
    private var innerRadius = 50f

    init {
        holder.addCallback(this)
    }

    override fun surfaceChanged(p0: SurfaceHolder, format: Int, width: Int, height: Int) {
//        center.x = width / 2f
//        center.y = height / 2f
//        outerRadius = Math.min(width, height) / 2f - paint.strokeWidth
//        innerRadius = outerRadius / 3
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        val canvas = holder.lockCanvas()
        canvas.drawColor(Color.WHITE)
//        canvas.drawCircle(center.x, center.y, outerRadius, paint)
        canvas.drawCircle(center.x, center.y, innerRadius, paint)
        holder.unlockCanvasAndPost(canvas)
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when(event?.action) {
            MotionEvent.ACTION_DOWN -> {
                val canvas = holder.lockCanvas()
                canvas.drawCircle(event.x, event.y, innerRadius, paint)
                holder.unlockCanvasAndPost(canvas)
            }
        }
        return super.onTouchEvent(event)
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.let {
            canvas.drawCircle(center.x, center.y, outerRadius, paint)
            canvas.drawCircle(center.x, center.y, innerRadius, paint)
        }
    }
}
