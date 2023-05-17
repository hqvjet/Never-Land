package com.hereams.neverland.gameObjects.view.component.map

import android.graphics.Canvas
import android.graphics.Rect
import com.hereams.neverland.constant.TILE_IMMOVEABLE
import com.hereams.neverland.gameObjects.view.component.map.tilemap_list.*
import com.hereams.neverland.graphics.SpritesSheet


open abstract class Tile(val map_location_rect: Rect) {

    protected var type: String = ""

    companion object {
        fun getTile(id_tile_type: Int, sprite_sheet: SpritesSheet, map_location_rect: Rect): Tile? {
            if (id_tile_type == 0)
                return Grass(sprite_sheet, map_location_rect)
            else if (id_tile_type == 1)
                return Lava(sprite_sheet, map_location_rect)
            else if (id_tile_type == 2)
                return Water(sprite_sheet, map_location_rect)
            else if (id_tile_type == 3)
                return Sand(sprite_sheet, map_location_rect)
            else if (id_tile_type == 4)
                return Stone(sprite_sheet, map_location_rect)
            else if (id_tile_type == 5)
                return SmoothStone(sprite_sheet, map_location_rect)
            else if (id_tile_type == 6)
                return Gravel(sprite_sheet, map_location_rect)
            else if (id_tile_type == 7)
                return Dirt(sprite_sheet, map_location_rect)
            else if (id_tile_type == 8)
                return Ice(sprite_sheet, map_location_rect)
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