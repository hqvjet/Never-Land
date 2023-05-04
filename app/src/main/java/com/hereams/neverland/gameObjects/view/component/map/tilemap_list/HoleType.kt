package com.hereams.neverland.gameObjects.view.component.map.tilemap_list

import android.graphics.Canvas
import android.graphics.PointF
import android.graphics.Rect
import com.hereams.neverland.constant.TILE_IMMOVEABLE
import com.hereams.neverland.gameObjects.view.component.map.Tile
import com.hereams.neverland.graphics.Sprites
import com.hereams.neverland.graphics.SpritesSheet

class HoleType(spriteSheet: SpritesSheet, mapLocationRect: Rect) : Tile(mapLocationRect) {

    private lateinit var sprites: Array<Sprites>

    init {
        sprites = spriteSheet.getHoleSprites()
        type = TILE_IMMOVEABLE
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