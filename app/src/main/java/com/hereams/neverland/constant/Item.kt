package com.hereams.neverland.constant

//Equipment
val STEEL_SWORD = 0
val IRON_SWORD = 1
val REFINED_SWORD = 2
private val eqKey = 2

//Potion
val HP_POTION = eqKey + 1
//val MP_POTION = eqKey + 2

val ITEM_NAME = arrayOf("Steel Sword", "Iron Sword", "Refined Sword", "Life Potion")
val ITEM_STAT = arrayOf(5, 10, 15, 50) //for equipment, it's atk stat. For potion, it's heal amounts
val ITEM_CLASSIFY = arrayOf(COMMON, ELITE, LEGENDARY, COMMON)
val ITEM_PRICE = arrayOf(3, 8, 20, 5)
val ITEM_DISCARDABLE = arrayOf(true, true, true, true)
val ITEM_TRADEALBE = arrayOf(true, true, true, true)
//val ITEM_SPIRITS

//Item Description
val ITEM_DESCRIPTION = arrayOf("Coming Soon", "Coming Soon", "Coming Soon", "Coming Soon")