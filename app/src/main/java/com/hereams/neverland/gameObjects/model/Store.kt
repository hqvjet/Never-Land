package com.hereams.neverland.gameObjects.model

import com.hereams.neverland.gameObjects.model.Item

class Store(name: String, description: String, items: Array<Item>, owner: String) {

    private lateinit var store_name: String
    private lateinit var store_description: String
    private lateinit var store_items: Array<Item>
    private lateinit var store_owner: String

    init {
        store_name = name
        store_description = description
        store_items = items
        store_owner = owner
    }

    fun getStoreName() = store_name
    fun setStoreName(value: String) { store_name = value }

    fun getStoreDescription() = store_description
    fun setStoreDescription(value: String) { store_description = value }

    fun getStoreItems() = store_items
    fun setStoreItems(value: Array<Item>) { store_items = value }

    fun getStoreOwner() = store_owner
    fun setStoreOwner(value: String) { store_owner = value }

}