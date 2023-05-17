package com.hereams.neverland.gameObjects.view.component.map

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect
import com.hereams.neverland.constant.SPRITES_SIZE
import com.hereams.neverland.gameObjects.view.component.enemy.EnemyView
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.SpritesSheet

class TileMap(val spritesSheet_array: MutableList<SpritesSheet>, val map_layout: GameMap) {

    private lateinit var map: Bitmap
    private lateinit var row: Number
    private lateinit var column: Number
    private lateinit var tile_map: MutableList<MutableList<Tile>>
    private lateinit var enemy: MutableList<EnemyView>
    private lateinit var obstacle_list: MutableList<Tile>

    init {
        obstacle_list = mutableListOf()
        initializeTileMap()
        enemy = map_layout.getEnemy()
    }

    private fun initializeTileMap() {
        val layout = map_layout.getLayout()
        row = map_layout.getNumberOfRowTiles()
        column = map_layout.getNumberOfColumnTiles()

        tile_map = mutableListOf()
        for (i in 0 until column.toInt()) {
            var row_list: MutableList<Tile> = mutableListOf()
            for (j in 0 until row.toInt()) {
                try {
                    row_list.add(
                        Tile.getTile(
                            layout[i][j],
                            spritesSheet_array[layout[i][j]],
                            getRectByIndex(i, j)
                        )!!
                    )
                }
                catch (e: java.lang.NullPointerException) {
                    println("$i $j")
                }


                //check for obstacles
                if (row_list[j]?.isObstacle() == true)
                    obstacle_list.add(row_list[j])
            }
            tile_map.add(row_list)
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

    fun getObstacles(): MutableList<Tile> {
        return obstacle_list
    }

    fun getEnemy(): MutableList<EnemyView> {
        return enemy
    }

    fun removeEnemy(index: Int) {
        enemy.removeAt(index)
    }

    fun reviveAll() {
        map_layout.initializeEnemy()
        enemy = map_layout.getEnemy()
    }

}