package com.hereams.neverland.gameObjects.view.component.map

import android.content.Context
import android.graphics.PointF
import com.hereams.neverland.constant.CIRCLE_RADIUS
import com.hereams.neverland.constant.SKELETON
import com.hereams.neverland.constant.SPRITES_SIZE
import com.hereams.neverland.gameObjects.view.component.CharacterView
import com.hereams.neverland.gameObjects.view.component.EnemyView

class TheHallWay(val context: Context, val player: CharacterView) : GameMap() {
    private val NUMBER_OF_ROW_TILES = 5
    private val NUMBER_OF_COLUMN_TILES = 5
    private lateinit var enemy: MutableList<EnemyView>

    private lateinit var layout: Array<Array<Int>>

    init {
        initializeLayout()
        initializeEnemy()
    }

    override fun getLayout(): Array<Array<Int>> {
        return layout
    }

    override fun getNumberOfRowTiles(): Int {
        return NUMBER_OF_ROW_TILES
    }

    override fun getNumberOfColumnTiles(): Int {
        return NUMBER_OF_COLUMN_TILES
    }

    override fun getEnemy(): MutableList<EnemyView> {
        return enemy
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

    private fun initializeEnemy() {
        enemy = mutableListOf(
            EnemyView(context, SKELETON, player, PointF(0f, 0f), CIRCLE_RADIUS),
            EnemyView(context, SKELETON, player, PointF(30f, 60f), CIRCLE_RADIUS),
            EnemyView(context, SKELETON, player, PointF(200f, 400f), CIRCLE_RADIUS),
            EnemyView(context, SKELETON, player, PointF(300f, 600f), CIRCLE_RADIUS),
            EnemyView(context, SKELETON, player, PointF(1000f, 60f), CIRCLE_RADIUS)
        )


    }

}