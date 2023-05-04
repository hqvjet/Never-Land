package com.hereams.neverland.gameObjects.view.component.map

import android.graphics.Canvas
import android.graphics.Rect
import com.hereams.neverland.constant.TILE_IMMOVEABLE
import com.hereams.neverland.gameObjects.view.component.map.tilemap_list.EarthType
import com.hereams.neverland.gameObjects.view.component.map.tilemap_list.HoleType
import com.hereams.neverland.graphics.SpritesSheet


open abstract class Tile(val map_location_rect: Rect) {

    protected var type: String = ""

    companion object {
        fun getTile(id_tile_type: Int, sprite_sheet: SpritesSheet, map_location_rect: Rect): Tile? {
            if (id_tile_type == 0)
                return EarthType(sprite_sheet, map_location_rect)
            else if (id_tile_type == 1)
                return HoleType(sprite_sheet, map_location_rect)
            else
                return null
        }
    }

    fun isObstacle(): Boolean {
        return type == TILE_IMMOVEABLE
    }

    fun getRect(): Rect {
        return map_location_rect
    }

    abstract fun draw(canvas: Canvas?)
}