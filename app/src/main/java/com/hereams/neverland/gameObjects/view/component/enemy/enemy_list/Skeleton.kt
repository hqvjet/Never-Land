package com.hereams.neverland.gameObjects.view.component.enemy.enemy_list

import android.content.Context
import android.graphics.PointF
import com.hereams.neverland.gameObjects.model.Inventory
import com.hereams.neverland.gameObjects.view.component.character.CharacterView
import com.hereams.neverland.gameObjects.view.component.enemy.EnemyView
import com.hereams.neverland.gameObjects.view.component.item.Item
import com.hereams.neverland.gameObjects.view.component.item.SteelSword

class Skeleton(
    val context: Context,
    enemy_id: Int,
    target: CharacterView,
    position: PointF,
    radius: Float
): EnemyView(context, enemy_id, target, position, radius) {

    private lateinit var drop_list: MutableList<Item>

    init {
        drop_list = mutableListOf(
            SteelSword(context, 1)
        )
    }

    override fun drop(character_inventory: Inventory) {
        // get drop list
        // calc item drop rate
        // get item by drop rate
        // add to inventory

        val drop_rate = model.getEnemyItemDropRate()
        val random = Math.random()

        if(random <= drop_rate) {
            character_inventory.addItem(drop_list.random())
        }

    }

}