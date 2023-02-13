package com.hereams.neverland.gameObjects.model

class NPC(name: String, greetings: Array<String>, seller: Boolean) {

    private lateinit var NPC_name: String
    private lateinit var greeting: Array<String>
    private var is_seller = false

    init {
        NPC_name = name
        greeting = greetings
        is_seller = seller
    }

    fun getNPCName() = NPC_name
    fun setNPCName(value: String) {
        NPC_name = value
    }

    fun getGreeting() = greeting
    fun setGreeting(value: Array<String>) {
        greeting = value
    }

    fun getIsSeller() = is_seller
    fun setIsSeller(value: Boolean) {
        is_seller = value
    }

}