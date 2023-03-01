package com.hereams.neverland.gameObjects.model

class Character {

    private lateinit var player_name: String
    private lateinit var player_level: Number
    private lateinit var player_exp: Number
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

    private lateinit var player_weapon: Weapon
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
    fun setPlayerExp(value: Int) {
        player_exp = value
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

    fun getWeapon() = player_weapon
    fun setWeapon(value: Weapon) {
        player_weapon = value
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


    constructor(
        name: String, level: Int, exp: Int, char_class: String,
        floor: Int, savePoint: Int, atk: Int, def: Int, hp: Int,
        VIT: Int, STR: Int, DEX: Int, INT: Int, CRT: Int, moveS: Float,
        weapon: Weapon, inventory: Inventory, option: Option
    ) {
        setPlayerName(name)
        setPlayerLevel(level)
        setPlayerExp(exp)
        setPlayerClass(char_class)
        setCurrentFloor(floor)
        setSavepoint(savePoint)
        setPlayerAttack(atk)
        setPlayerDef(def)
        setPlayerHp(hp)
        setVIT(VIT)
        setINT(INT)
        setSTR(STR)
        setDEX(DEX)
        setCRT(CRT)
        setWeapon(weapon)
        setInventory(inventory)
        setOption(option)
        setMoveSpeed(moveS)
    }

}