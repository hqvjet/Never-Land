package com.hereams.neverland.graphics

import android.graphics.*

class Sprites(private val spritesSheet: SpritesSheet, private val rect: Rect) {

    fun draw(canvas: Canvas?, position: PointF) {
        canvas?.drawColor(Color.TRANSPARENT, PorterDuff.Mode.CLEAR)
        canvas?.drawBitmap(
            spritesSheet.getBitmap(),
            rect,
            RectF(position.x, position.y, position.x + rect.width(), position.y + rect.height()),
            null
        )
    }

}