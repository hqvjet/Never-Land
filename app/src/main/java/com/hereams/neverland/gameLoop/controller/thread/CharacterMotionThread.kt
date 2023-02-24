package com.hereams.neverland.gameLoop.controller.thread

import com.hereams.neverland.gameObjects.view.CharacterView

class CharacterMotionThread(private val view: CharacterView) : Thread() {

    private var running: Boolean = false

    fun turnOn() {
        running = true
        start()
    }

    fun turnOff() {
        running = false
        join()
    }

    override fun run() {
        while (running){
            view.service.render()
//            println("running: ${view.position.x} ${view.position.y}")
        }

    }

}
