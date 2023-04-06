package com.hereams.neverland.gameObjects.view.map

import android.graphics.Canvas
import android.graphics.Rect
import com.hereams.neverland.graphics.SpritesSheet


open abstract class Tile(val map_location_rect: Rect) {

    companion object {
        fun getTile(id_tile_type: Int, sprite_sheet: SpritesSheet, map_location_rect: Rect): Tile? {
            if (id_tile_type == 0)
                return EarthType(sprite_sheet, map_location_rect)
            else
                return null
        }
    }

    abstract fun draw(canvas: Canvas?)
}