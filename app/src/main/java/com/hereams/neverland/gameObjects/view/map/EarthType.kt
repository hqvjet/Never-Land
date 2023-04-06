package com.hereams.neverland.gameObjects.view.map

import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.Rect
import com.hereams.neverland.graphics.Sprites
import com.hereams.neverland.graphics.SpritesSheet

class EarthType(spriteSheet: SpritesSheet, mapLocationRect: Rect) : Tile(mapLocationRect) {

    private lateinit var sprites: Array<Sprites>

    init {
        sprites = spriteSheet.getEarthSprites()
    }

    override fun draw(canvas: Canvas?) {
        for (i in sprites.indices) {
            sprites[i].draw(
                canvas, PointF(
                    map_location_rect.left.toFloat(),
                    map_location_rect.top.toFloat()
                )
            )
        }
    }

}
