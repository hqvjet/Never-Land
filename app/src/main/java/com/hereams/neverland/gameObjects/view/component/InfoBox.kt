package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.util.AttributeSet
import android.view.View
import com.hereams.neverland.R

class InfoBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private lateinit var bitmap: Bitmap

    init {
        setBackgroundColor(Color.TRANSPARENT)
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.info_box)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        println("infobox")
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

}