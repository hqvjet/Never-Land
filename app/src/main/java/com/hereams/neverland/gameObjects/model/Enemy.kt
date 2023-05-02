package com.hereams.neverland.gameObjects.model

import com.hereams.neverland.constant.*

class Enemy(
    level: Int, val enemy_id: Int
) {

    private lateinit var enemy_name: String
    private lateinit var enemy_level: Number
    private lateinit var enemy_classify: String
    private lateinit var enemy_def: Number
    private lateinit var enemy_attack: Number
    private lateinit var enemy_hp: Number
    private lateinit var enemy_move_speed: Number
    private lateinit var enemy_attack_speed: Number
    private var enemy_drop_rate: Double = 0.0
    private var enemy_exp_amount: Int = 0
    private var enemy_gold_amount: Int = 0

    init {
        enemy_name = ENEMY_NAME[enemy_id]
        enemy_level = level
        enemy_classify = ENEMY_CLASSIFY[enemy_id]
        enemy_def = ENEMY_DEF[enemy_id]
        enemy_attack = ENEMY_ATK[enemy_id]
        enemy_hp = ENEMY_HP[enemy_id]
        enemy_move_speed = ENEMY_MOVE_SPEED[enemy_id]
        enemy_attack_speed = ENEMY_ATTACK_SPEED[enemy_id]
        enemy_drop_rate = ENEMY_ITEM_DROP_RATE[enemy_id]
        enemy_exp_amount = ENEMY_EXP_AMOUNT[enemy_id]
        enemy_gold_amount = ENEMY_GOLD_AMOUNT[enemy_id]
    }

    fun getEnemyName() = enemy_name
    fun setEnemyName(value: String) {
        enemy_name = value
    }

    fun getEnemyLevel() = enemy_level
    fun setEnemyLevel(value: Number) {
        enemy_level = value
    }

    fun getEnemyClassify() = enemy_classify
    fun setEnemyClassify(value: String) {
        enemy_classify = value
    }

    fun getEnemyDef() = enemy_def
    fun setEnemyDef(value: Number) {
        enemy_def = value
    }

    fun getEnemyAttack() = enemy_attack
    fun setEnemyAttack(value: Number) {
        enemy_attack = value
    }

    fun getEnemyHp() = enemy_hp
    fun setEnemyHp(value: Number) {
        enemy_hp = value
    }

    fun getEnemyMoveSpeed() = enemy_move_speed
    fun setEnemyMoveSpeed(value: Number) {
        enemy_move_speed = value
    }

    fun getAttackSpeed() = enemy_attack_speed
    fun setAttackSpeed(value: Number) {
        enemy_attack_speed = value
    }

    fun getEnemyItemDropRate(): Double {
        return enemy_drop_rate
    }

    fun getEnemyEXPAmount(): Int {
        return enemy_exp_amount
    }

    fun getEnemyGoldAmount(): Int {
        return enemy_gold_amount
    }
}