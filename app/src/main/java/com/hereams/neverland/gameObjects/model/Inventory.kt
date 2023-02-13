package com.hereams.neverland.gameObjects.model

class Inventory(slot: Int, gold: Int, items: MutableList<Item>) {
    private lateinit var inventory_slot: Number
    private lateinit var inventory_gold: Number
    private lateinit var inventory_items: MutableList<Item>

    init {
        inventory_slot = slot
        inventory_gold = gold
        inventory_items = items
    }

    fun getInventorySlot() = inventory_slot
    fun setInventorySlot(value: Int) {
        inventory_slot = value
    }

    fun getInventoryGold() = inventory_gold
    fun setInventoryGold(value: Int) {
        inventory_gold = value
    }

    fun addItem(item: Item) {
        inventory_items.add(item)
    }

    fun removeItem(item: Item) {
        inventory_items.remove(item)
    }

    fun getAmountOfItem() = inventory_items.size
}