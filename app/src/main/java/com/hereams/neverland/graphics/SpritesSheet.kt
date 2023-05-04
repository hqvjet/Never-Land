package com.hereams.neverland.graphics

import android.content.Context
import android.graphics.*
import com.hereams.neverland.R
import com.hereams.neverland.constant.*
import kotlin.math.floor

class SpritesSheet(
    private val context: Context,
    private val image_id: Int,
    private val direction: Number?,
    private val binding_condition: String?,
    private val serving_object: Int
) {
    private var bitmapOptions: BitmapFactory.Options = BitmapFactory.Options()
    private lateinit var bitmap: Bitmap
    private val zoomMatrix = Matrix()


    init {
        bitmapOptions.inScaled = false
        zoomMatrix.setScale(8f, 8f)

        if (serving_object == CHARACTER || serving_object == ENEMY || serving_object == TILEMAP) {
            if (direction == null) {
                bitmap = BitmapFactory.decodeResource(context.resources, image_id, bitmapOptions)
            } else {
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
        }

        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, zoomMatrix, true)

    }

    fun getCharacterSpritesArray(): Array<Sprites> {

        val fixed: Int = if (binding_condition == MOVE_SPRITE) 1 else 1
        val number_of_sprites =
            if (binding_condition == MOVE_SPRITE)
                MAX_MOVEMENT_SPRITES_SIZE_OF_CHARACTER
            else
                MAX_ATTACK_SPRITES_SIZE_OF_CHARACTER

        val spritesArray = Array(number_of_sprites) { i ->
            Sprites(
                this, Rect(
                    (i % number_of_sprites) * SPRITES_SIZE,
                    fixed * SPRITES_SIZE,
                    (i % number_of_sprites + 1) * SPRITES_SIZE,
                    (fixed + 1) * SPRITES_SIZE
                )
            )
        }
        return spritesArray
    }

    fun getEnemyMovementSpritesArray(): Array<Sprites> {

        val spritesArray = Array(MAX_MOVEMENT_SPRITES_SIZE_OF_ENEMY) { i ->
            Sprites(
                this, Rect(
                    (i % (MAX_MOVEMENT_SPRITES_SIZE_OF_ENEMY / 2)) * SPRITES_SIZE,
                    (floor(i.toFloat() / (MAX_MOVEMENT_SPRITES_SIZE_OF_ENEMY / 2)) - 1).toInt(),
                    ((i % (MAX_MOVEMENT_SPRITES_SIZE_OF_ENEMY / 2)) + 1) * SPRITES_SIZE,
                    SPRITES_SIZE
                )
            )
        }
        return spritesArray
    }

    fun getBitmap(): Bitmap {
        return bitmap
    }
    fun getEarthSprites(): Array<Sprites> {
        return arrayOf(
            Sprites(this, Rect(SPRITES_SIZE, SPRITES_SIZE, SPRITES_SIZE * 2, SPRITES_SIZE * 2))
        )
    }

    fun getHoleSprites(): Array<Sprites> {
        return arrayOf(
            Sprites(this, Rect(SPRITES_SIZE * 6, 0, SPRITES_SIZE * 7, SPRITES_SIZE))
        )
    }
}