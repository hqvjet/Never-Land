package com.hereams.neverland.gameObjects.model

open class Item {

    private lateinit var item_id: Number
    private lateinit var item_name: String
    private lateinit var item_description: String
    private lateinit var item_price: Number
    private lateinit var item_classify: String
    private var discardable = false
    private var tradeable = false

    constructor(
        name: String, description: String, price: Number,
        classify: String, discardable: Boolean, tradeable: Boolean
    ) {

        setItemName(name)
        setItemDescription(description)
        setItemPrice(price)
        setItemClassify(classify)
        setDiscardable(discardable)
        setTradeable(tradeable)

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
}