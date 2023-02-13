package com.hereams.neverland.gameObjects.model

class Map(name: String, permission: Boolean) {

    private lateinit var map_name: String
    private var have_permission = false
//    private lateinit var map_sprites

    init {
        map_name = name
        have_permission = permission
    }

    fun getMapName() = map_name
    fun setMapName(value: String) {
        map_name = value
    }

    fun getHavePermission() = have_permission
    fun setHavePermission(value: Boolean) {
        have_permission = value
    }

}