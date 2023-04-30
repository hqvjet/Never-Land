package com.hereams.neverland.gameObjects.model

import com.hereams.neverland.gameObjects.model.Item

class Potion: Item {

    private lateinit var potion_heal_amount: Number
    private lateinit var potion_category: String
    private lateinit var potion_stack: Number
//    private lateinit var potion_sprites

    constructor(
        potion_id: Int, stack: Number
    ) : super(potion_id, stack as Int) {

//        setPotionHealAmount(heal)
//        setPotionStack(stack)

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