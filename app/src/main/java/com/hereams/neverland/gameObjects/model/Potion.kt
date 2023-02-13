package com.hereams.neverland.gameObjects.model

import com.hereams.neverland.gameObjects.model.Item

class Potion: Item {

    private lateinit var potion_heal_amount: Number
    private lateinit var potion_category: String
    private lateinit var potion_stack: Number
//    private lateinit var potion_sprites

    constructor(
        name: String, description: String, price: Number,
        classify: String, discardable: Boolean, tradeable: Boolean,
        heal: Number, stack: Number
    ) : super(name, description, price, classify, discardable, tradeable) {

        setPotionHealAmount(heal)
        setPotionStack(stack)

    }

    fun getPotionHealAmount() = potion_heal_amount
    fun setPotionHealAmount(value: Number) {
        potion_heal_amount = value
    }

    fun getPotionCategory() = potion_category
    fun setPotionCategory(value: String) {
        potion_category = value
    }

    fun getPotionStack() = potion_stack
    fun setPotionStack(value: Number) {
        potion_stack = value
    }

}