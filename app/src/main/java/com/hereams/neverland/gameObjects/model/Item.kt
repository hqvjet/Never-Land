package com.hereams.neverland.gameObjects.model

import com.hereams.neverland.constant.*

open class Item {

    private lateinit var item_id: Number
    private lateinit var item_name: String
    private lateinit var item_description: String
    private lateinit var item_price: Number
    private lateinit var item_classify: String
    private lateinit var item_quantity: Number
    private var discardable = false
    private var tradeable = false

    constructor(
        item_id: Int, quantity: Int
    ) {

        setItemName(ITEM_NAME[item_id])
        setItemDescription(ITEM_DESCRIPTION[item_id])
        setItemPrice(ITEM_PRICE[item_id])
        setItemClassify(ITEM_CLASSIFY[item_id])
        setDiscardable(ITEM_DISCARDABLE[item_id])
        setTradeable(ITEM_TRADEALBE[item_id])

    }

    fun getItemId(): Number {
        return item_id
    }

    fun setItemId(id: Number) {
        item_id = id
    }

    fun getItemName(): String {
        return item_name
    }

    fun setItemName(name: String) {
        item_name = name
    }

    fun getItemDescription(): String {
        return item_description
    }

    fun setItemDescription(description: String) {
        item_description = description
    }

    fun getItemPrice(): Number {
        return item_price
    }

    fun setItemPrice(price: Number) {
        item_price = price
    }

    fun getItemClassify(): String {
        return item_classify
    }

    fun setItemClassify(classify: String) {
        item_classify = classify
    }

    fun isDiscardable(): Boolean {
        return discardable
    }

    fun setDiscardable(isDiscardable: Boolean) {
        discardable = isDiscardable
    }

    fun isTradeable(): Boolean {
        return tradeable
    }

    fun setTradeable(isTradeable: Boolean) {
        tradeable = isTradeable
    }

    fun getQuantity(): Int {
        return item_quantity.toInt()
    }

    fun setQuantity(value: Int) {
        item_quantity = value
    }
}