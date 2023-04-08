package com.hereams.neverland.gameLoop.controller.thread

import com.hereams.neverland.gameObjects.GameObject

class AttackController(val atk_spd: Float, val serving_object: GameObject) : Thread() {

    var ready_to_attack: Boolean = true
    var object_attacked: Boolean = false
    var is_running: Boolean = false

    override fun run() {
    }

    fun stopLoop() {
        is_running = false
        try {
            join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

    fun startLoop() {
        is_running = true
        start()
    }

}