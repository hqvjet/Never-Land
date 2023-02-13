package com.hereams.neverland.gameObjects.model

class Option(lang: Number, user: String, pass: String) {

    private lateinit var language: Number
    private lateinit var username: String
    private lateinit var password: String

    init {
        language = lang
        username = user
        password = pass
    }

    fun getLanguage() = language
    fun setLanguage(value: Number) {
        language = value
    }

    fun getUsername() = username
    fun setUsername(value: String) {
        username = value
    }

    fun getPassword() = password
    fun setPassword(value: String) {
        password = value
    }

}