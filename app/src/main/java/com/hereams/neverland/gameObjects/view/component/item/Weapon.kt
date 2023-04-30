package com.hereams.neverland.gameObjects.view.component.item

import android.graphics.*

abstract class Weapon: Item() {

    override fun draw(rect: Rect, canvas: Canvas?) {

        // Vẽ khung hình trống
        canvas?.drawRect(rect, Paint().apply {
            color = Color.RED
            style = Paint.Style.STROKE
            strokeWidth = 2f
        })

        // Vẽ ảnh giữa trên hình vuông
        canvas?.drawBitmap(bitmap, null, rect, null)

    }

}