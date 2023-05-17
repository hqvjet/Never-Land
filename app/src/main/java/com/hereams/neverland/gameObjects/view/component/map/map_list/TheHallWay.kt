package com.hereams.neverland.gameObjects.view.component.map.map_list

import android.content.Context
import android.graphics.PointF
import com.hereams.neverland.constant.CIRCLE_RADIUS
import com.hereams.neverland.constant.SKELETON
import com.hereams.neverland.gameObjects.view.component.character.CharacterView
import com.hereams.neverland.gameObjects.view.component.enemy.EnemyView
import com.hereams.neverland.gameObjects.view.component.enemy.enemy_list.Skeleton
import com.hereams.neverland.gameObjects.view.component.map.GameMap
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class TheHallWay(val context: Context, val player: CharacterView) : GameMap() {
    private val NUMBER_OF_ROW_TILES = 10
    private val NUMBER_OF_COLUMN_TILES = 10
    private lateinit var enemy: MutableList<EnemyView>

    private lateinit var layout: Array<Array<Int>>

    private lateinit var thread: Thread

    init {
        enemy = mutableListOf()
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
            arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1),
            arrayOf(1, 0, 2, 0, 0, 8, 0, 0, 0, 1),
            arrayOf(1, 0, 0, 0, 0, 8, 0, 0, 0, 1),
            arrayOf(1, 4, 0, 0, 6, 8, 0, 0, 0, 1),
            arrayOf(1, 0, 1, 0, 1, 8, 0, 0, 0, 1),
            arrayOf(1, 0, 0, 0, 5, 8, 1, 0, 4, 1),
            arrayOf(1, 1, 2, 0, 0, 8, 2, 0, 4, 1),
            arrayOf(1, 0, 0, 0, 0, 8, 0, 3, 0, 1),
            arrayOf(1, 4, 0, 0, 6, 8, 0, 0, 0, 1),
            arrayOf(1, 1, 1, 1, 1, 1, 1, 1, 1, 1)
        )
    }

    override fun initializeEnemy() {
        val initThread = Thread {

            synchronized(enemy) {
                enemy = mutableListOf(
                    Skeleton(context, SKELETON, player, PointF(1000f, 1000f), CIRCLE_RADIUS),
                    Skeleton(context, SKELETON, player, PointF(2000f, 300f), CIRCLE_RADIUS),
                    Skeleton(context, SKELETON, player, PointF(2000f, 4000f), CIRCLE_RADIUS),
                    Skeleton(context, SKELETON, player, PointF(3000f, 3000f), CIRCLE_RADIUS),
                    Skeleton(context, SKELETON, player, PointF(1000f, 1500f), CIRCLE_RADIUS)
                )
            }

        }
        initThread.start()

    }
}