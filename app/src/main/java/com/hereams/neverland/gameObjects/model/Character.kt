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
    private var player_def = 0
    private var player_hp = 0
    private var statpoint: Int = 0
    private var VIT: Int = 0
    private var STR: Int = 0
    private var DEX: Int = 0
    private var INT: Int = 0
    private var CRT: Int = 0
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
    fun getPlayerExpRequiredToLevelUp() = player_level * 20
    /**
     * This function will calc the amount needed for level up character
     */
    fun setPlayerExp(value: Int) {
        player_exp += value
        while(player_exp >= player_level * 20) {
            player_exp -= player_level * 20
            ++player_level
            levelUp()
        }
    }

    private fun levelUp() {
        player_attack = (BASE_ATTACK[class_id.toInt()] + (STR / 5 + DEX / 3 + VIT / 10 + INT / 20 + 3) * player_level) + weapon.getWeaponAttack().toInt()
        player_hp = BASE_HP[class_id.toInt()] + (20 + VIT + INT / 2 + STR / 2 ) * player_level
        player_def = BASE_DEF[class_id.toInt()] + (STR / 5 + DEX / 3 + VIT / 10 + INT / 20 + 1) * player_level
        ++ statpoint
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
    fun setPlayerDef(value: Int) {
        player_def = value
    }

    fun getPlayerHp() = player_hp
    fun setPlayerHp(value: Int) {
        player_hp = value
    }
    fun getPlayerMaxHp() = BASE_HP[class_id.toInt()] + (20 + VIT + INT / 2 + STR / 2 ) * player_level

    fun getStatPoint() = statpoint
    fun setStatPoint(value: Int) {
        statpoint = value
    }

    fun getVIT() = VIT
    fun setVIT(value: Int) {
        VIT = value
    }

    fun getSTR() = STR
    fun setSTR(value: Int) {
        STR = value
    }

    fun getDEX() = DEX
    fun setDEX(value: Int) {
        DEX = value
    }

    fun getINT() = INT
    fun setINT(value: Int) {
        INT = value
    }

    fun getCRT() = CRT
    fun setCRT(value: Int) {
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
        setWeapon(weapon)
        setPlayerAttack((BASE_ATTACK[class_id] + (STR / 5 + DEX / 3 + VIT / 10 + INT / 20 + 3) * player_level) + weapon.getWeaponAttack().toInt())
        setPlayerDef(BASE_DEF[class_id] + (STR / 5 + DEX / 3 + VIT / 10 + INT / 20 + 1) * player_level)
        setPlayerHp(BASE_HP[class_id] + (20 + VIT + INT / 2 + STR / 2 ) * player_level)
        setVIT(VIT)
        setINT(INT)
        setSTR(STR)
        setDEX(DEX)
        setCRT(CRT)
        setInventory(inventory)
        setOption(option)
        setMoveSpeed(BASE_MOVE[class_id])
        setAttackSpeed(BASE_ATTACK_SPEED[class_id])
    }

}