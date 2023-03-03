package com.hereams.neverland.graphics

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.PointF
import android.graphics.Rect
import android.view.SurfaceView
import com.hereams.neverland.R
import com.hereams.neverland.constant.SPRITES_SIZE

class SpritesSheet(private val position: PointF, private val context: Context) {
    private var bitmapOptions: BitmapFactory.Options = BitmapFactory.Options()
    private lateinit var bitmap: Bitmap

    init {
        bitmapOptions.inScaled = false
        bitmap = BitmapFactory.decodeResource(context.resources, R.mipmap.character, bitmapOptions)
    }

    fun getCharacterSpritesArray(): Array<Sprites> {
        val spritesArray = Array(7) { i ->
            Sprites(this, Rect(i * SPRITES_SIZE, 0, (i + 1) * SPRITES_SIZE, SPRITES_SIZE))
        }
        return spritesArray
    }

    fun getBitmap(): Bitmap {
        return bitmap
    }
}