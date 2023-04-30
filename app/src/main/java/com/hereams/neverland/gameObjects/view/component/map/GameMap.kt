package com.hereams.neverland.gameObjects.view.component.map

import com.hereams.neverland.gameObjects.view.component.EnemyView

abstract class GameMap {

    abstract fun getLayout(): Array<Array<Int>>
    abstract fun getNumberOfRowTiles(): Int
    abstract fun getNumberOfColumnTiles(): Int
    abstract fun getEnemy(): MutableList<EnemyView>

}