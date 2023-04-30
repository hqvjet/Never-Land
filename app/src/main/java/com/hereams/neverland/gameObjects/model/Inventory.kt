package com.hereams.neverland.gameObjects.model

class Inventory(
    slot: Int, gold: Int,
) {
    private lateinit var inventory_slot: Number
    private lateinit var inventory_gold: Number
    private lateinit var inventory_items: MutableList<com.hereams.neverland.gameObjects.view.component.item.Item>

    init {
        inventory_slot = slot
        inventory_gold = gold
        inventory_items = mutableListOf()
    }

    fun getInventorySlot() = inventory_slot
    fun setInventorySlot(value: Int) {
        inventory_slot = value
    }

    fun getInventoryGold() = inventory_gold
    fun setInventoryGold(value: Int) {
        inventory_gold = value
    }

    fun addItem(item: com.hereams.neverland.gameObjects.view.component.item.Item) {
        inventory_items.add(item)
    }

    fun addItems(items: Array<com.hereams.neverland.gameObjects.view.component.item.Item>) {
        inventory_items.addAll(items)
    }

    fun removeItem(item: com.hereams.neverland.gameObjects.view.component.item.Item) {
        inventory_items.remove(item)
    }

    fun getItems(): MutableList<com.hereams.neverland.gameObjects.view.component.item.Item> {
        return inventory_items
    }

    fun getAmountOfItem() = inventory_items.size
}