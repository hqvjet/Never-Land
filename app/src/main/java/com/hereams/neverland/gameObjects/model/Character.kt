package com.hereams.neverland.gameObjects.model

import com.hereams.neverland.constant.*

class Character {

    private lateinit var player_name: String
    private var player_level: Int = 1
    private var player_exp: Int = 0
    private lateinit var player_class: String
    private lateinit var current_floor: Number
    private lateinit var savepoint: Number
    private lateinit var player_attack: Number
    private lateinit var player_def: Number
    private lateinit var player_hp: Number
    private lateinit var statpoint: Number
    private lateinit var VIT: Number
    private lateinit var STR: Number
    private lateinit var DEX: Number
    private lateinit var INT: Number
    private lateinit var CRT: Number
    private lateinit var player_move_speed: Number
    private lateinit var player_attack_speed: Number
    private lateinit var class_id: Number

    private lateinit var weapon: Weapon
    private lateinit var inventory: Inventory
    private lateinit var option: Option

    fun getPlayerName() = player_name
    fun setPlayerName(value: String) {
        player_name = value
    }

    fun getPlayerLevel() = player_level
    fun setPlayerLevel(value: Int) {
        player_level = value
    }

    fun getPlayerExp() = player_exp

    /**
     * This function will calc the amount needed for level up character
     */
    fun setPlayerExp(value: Int) {
        player_exp += value
        while(player_exp >= player_level * 20) {
            player_exp -= player_level + 20
            ++player_level
        }
    }

    fun getPlayerClass() = player_class
    fun setPlayerClass(value: String) {
        player_class = value
    }

    fun getCurrentFloor() = current_floor
    fun setCurrentFloor(value: Int) {
        current_floor = value
    }

    fun getSavepoint() = savepoint
    fun setSavepoint(value: Int) {
        savepoint = value
    }

    fun getPlayerAttack() = player_attack
    fun setPlayerAttack(value: Number) {
        player_attack = value
    }

    fun getPlayerDef() = player_def
    fun setPlayerDef(value: Number) {
        player_def = value
    }

    fun getPlayerHp() = player_hp
    fun setPlayerHp(value: Number) {
        player_hp = value
    }

    fun getStatPoint() = statpoint
    fun setStatPoint(value: Number) {
        statpoint = value
    }

    fun getVIT() = VIT
    fun setVIT(value: Number) {
        VIT = value
    }

    fun getSTR() = STR
    fun setSTR(value: Number) {
        STR = value
    }

    fun getDEX() = DEX
    fun setDEX(value: Number) {
        DEX = value
    }

    fun getINT() = INT
    fun setINT(value: Number) {
        INT = value
    }

    fun getCRT() = CRT
    fun setCRT(value: Number) {
        CRT = value
    }

    fun getWeapon() = weapon
    fun setWeapon(value: Weapon) {
        weapon = value
    }

    fun getInventory() = inventory
    fun setInventory(value: Inventory) {
        inventory = value
    }

    fun getOption() = option
    fun setOption(value: Option) {
        option = value
    }

    fun getMoveSpeed() = player_move_speed
    fun setMoveSpeed(value: Number) {
        player_move_speed = value
    }

    fun getClassId() = class_id
    fun setClassId(value: Number) {
        class_id = value
    }

    fun getAttackSpeed() = player_attack_speed
    fun setAttackSpeed(value: Number) {
        player_attack_speed = value
    }


    constructor(
        name: String, level: Int, exp: Int, class_id: Int,
        floor: Int, savePoint: Int,
        VIT: Int, STR: Int, DEX: Int, INT: Int, CRT: Int,
        weapon: Weapon, inventory: Inventory, option: Option
    ) {
        setPlayerLevel(level)
        setClassId(class_id)
        setPlayerExp(exp)
        setPlayerClass(CLASS_NAME[class_id])
        setCurrentFloor(floor)
        setSavepoint(savePoint)
        setPlayerAttack(BASE_ATTACK[class_id])
        setPlayerDef(BASE_DEF[class_id])
        setPlayerHp(BASE_HP[class_id])
        setVIT(VIT)
        setINT(INT)
        setSTR(STR)
        setDEX(DEX)
        setCRT(CRT)
        setWeapon(weapon)
        setInventory(inventory)
        setOption(option)
        setMoveSpeed(BASE_MOVE[class_id])
        setAttackSpeed(BASE_ATTACK_SPEED[class_id])
    }

}