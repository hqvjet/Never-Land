package com.hereams.neverland.gameObjects.view.map

import com.hereams.neverland.constant.SPRITES_SIZE

class TheHallWay {
    private val TILE_WIDTH_PIXELS = SPRITES_SIZE
    private val TILE_HEIGHT_PIXELS = SPRITES_SIZE
    private val NUMBER_OF_ROW_TILES = 5
    private val NUMBER_OF_COLUMN_TILES = 5

    private lateinit var layout: Array<Array<Int>>

    init {
        initializeLayout()
    }

    fun getLayout(): Array<Array<Int>> {
        return layout
    }

    fun getNumberOfRowTiles(): Int {
        return NUMBER_OF_ROW_TILES
    }

    fun getNumberOfColumnTiles(): Int {
        return NUMBER_OF_COLUMN_TILES
    }

    private fun initializeLayout() {
        layout = arrayOf(
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0),
            arrayOf(0, 0, 0, 0, 0)
        )
    }
}