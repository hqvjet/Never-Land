package com.hereams.neverland.gameObjects.model

class Enemy(
    name: String, level: Int, classify: String, def: Int, attack: Int, hp: Int
) {

    private lateinit var enemy_name: String
    private lateinit var enemy_level: Number
    private lateinit var enemy_classify: String
    private lateinit var enemy_def: Number
    private lateinit var enemy_attack: Number
    private lateinit var enemy_hp: Number
//    private lateinit var enemy_sprites

    init {
        enemy_name = name
        enemy_level = level
        enemy_classify = classify
        enemy_def = def
        enemy_attack = attack
        enemy_hp = hp
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

}