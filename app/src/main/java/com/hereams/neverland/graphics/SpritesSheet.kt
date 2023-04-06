package com.hereams.neverland.graphics

import android.content.Context
import android.graphics.*
import com.hereams.neverland.R
import com.hereams.neverland.constant.DIRECTION_LEFT
import com.hereams.neverland.constant.MAX_MOVEMENT_SPRITES_SIZE
import com.hereams.neverland.constant.SPRITES_SIZE
import kotlin.math.floor

class SpritesSheet(
    private val context: Context,
    private val image_id: Int,
    private val direction: Number?
) {
    private var bitmapOptions: BitmapFactory.Options = BitmapFactory.Options()
    private lateinit var bitmap: Bitmap
    private val zoomMatrix = Matrix()


    init {
        bitmapOptions.inScaled = false
        zoomMatrix.setScale(8f, 8f)

        if (direction == null) {
            bitmap = BitmapFactory.decodeResource(context.resources, image_id, bitmapOptions)
        }
        else {
            val bitmapRight: Bitmap = BitmapFactory.decodeResource(
                context.resources,
                image_id,
                bitmapOptions
            )
            val matrix =
                Matrix().apply {
                    postScale(
                        -1f,
                        1f,
                        bitmapRight.width / 2f,
                        bitmapRight.height / 2f
                    )
                }
            bitmap = Bitmap.createBitmap(
                bitmapRight,
                0,
                0,
                bitmapRight.width,
                bitmapRight.height,
                matrix,
                true
            )
        }
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, zoomMatrix, true)

    }

    fun getCharacterMovementSpritesArray(): Array<Sprites> {

        val spritesArray = Array(MAX_MOVEMENT_SPRITES_SIZE) { i ->
            Sprites(
                this, Rect(
                    (i % (MAX_MOVEMENT_SPRITES_SIZE / 2)) * SPRITES_SIZE,
                    (floor(i.toFloat() / (MAX_MOVEMENT_SPRITES_SIZE / 2)) - 1).toInt(),
                    ((i % (MAX_MOVEMENT_SPRITES_SIZE / 2)) + 1) * SPRITES_SIZE,
                    SPRITES_SIZE
                )
            )
        }
        return spritesArray
    }

    fun getEarthSprites(): Array<Sprites> {
        return arrayOf(
            Sprites(this, Rect(SPRITES_SIZE, SPRITES_SIZE, SPRITES_SIZE * 2, SPRITES_SIZE * 2))
        )
    }

    fun getBitmap(): Bitmap {
        return bitmap
    }
}