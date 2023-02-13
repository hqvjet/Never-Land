package com.hereams.neverland.gameObjects.model

import com.hereams.neverland.gameObjects.model.Item

class Weapon : Item {

    private lateinit var weapon_attack: Number
    private lateinit var enhance_level: Number
    private var status = false // is wore ?

    constructor(
        name: String, description: String, price: Number,
        classify: String, discardable: Boolean, tradeable: Boolean,
        atk: Number, enhance_level: Number
    ) : super(name, description, price, classify, discardable, tradeable) {

        setWeaponAttack(atk)
        setEnhanceLevel(enhance_level)

    }

    fun getWeaponAttack(): Number {
        return weapon_attack
    }

    fun setWeaponAttack(atk: Number) {
        weapon_attack = atk
    }

    fun getEnhanceLevel(): Number {
        return enhance_level
    }

    fun setEnhanceLevel(lvl: Number) {
        enhance_level = lvl
    }

    fun getStatus()= status
    fun setStatus(new_status: Boolean) {
        status = new_status
    }

    fun equipment() {

    }

}