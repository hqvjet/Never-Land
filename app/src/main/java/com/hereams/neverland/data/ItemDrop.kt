package com.hereams.neverland.data

import com.hereams.neverland.gameObjects.view.component.item.Item

class ItemDrop(val item: Item, val drop_rate: Double) {

    @JvmName("getItem1")
    fun getItem(): Item {
        return item
    }

    fun getDropRate(): Float {
        return drop_rate.toFloat()
    }

}