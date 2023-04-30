package com.hereams.neverland.gameObjects.view.component.map

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.hereams.neverland.constant.SPRITES_SIZE
import com.hereams.neverland.gameObjects.view.component.EnemyView
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.SpritesSheet

class TileMap(val spritesSheet: SpritesSheet, val map_layout: GameMap) {

    private lateinit var map: Bitmap
    private lateinit var row: Number
    private lateinit var column: Number
    private lateinit var tile_map: Array<Array<Tile?>>
    private lateinit var enemy: MutableList<EnemyView>

    init {
        initializeTileMap()
        enemy = map_layout.getEnemy()
    }

    private fun initializeTileMap() {
        val layout = map_layout.getLayout()
        row = map_layout.getNumberOfRowTiles()
        column = map_layout.getNumberOfColumnTiles()

        tile_map = Array(column.toInt()) { arrayOfNulls<Tile?>(row.toInt()) }
        for (i in 0 until column.toInt()) {
            for (j in 0 until row.toInt()) {
                tile_map[i][j] = Tile.getTile(
                    layout[i][j],
                    spritesSheet,
                    getRectByIndex(i, j)
                )!!
            }
        }

        val config = Bitmap.Config.ARGB_8888
        map = Bitmap.createBitmap(
            row.toInt() * SPRITES_SIZE,
            column.toInt() * SPRITES_SIZE,
            config
        )

        val map_canvas: Canvas = Canvas(map)

        for (i in 0 until column.toInt()) {
            for (j in 0 until row.toInt()) {
                tile_map[i][j]?.draw(map_canvas)
            }
        }
    }

    fun draw(canvas: Canvas?, game_display: GameDisplay) {
        canvas?.drawBitmap(
            map,
            game_display.getGameRect(),
            game_display.DISPLAY_RECT,
            null
        )
    }

    private fun getRectByIndex(idxRow: Int, idxCol: Int): Rect {
        return Rect(
            idxCol * SPRITES_SIZE,
            idxRow * SPRITES_SIZE,
            (idxCol + 1) * SPRITES_SIZE,
            (idxRow + 1) * SPRITES_SIZE
        )
    }

    fun getEnemy(): MutableList<EnemyView> {
        return enemy
    }

    fun removeEnemy(index: Int) {
        enemy.removeAt(index)
    }

}