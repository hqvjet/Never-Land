package com.hereams.neverland.gameLoop.controller.thread

import com.hereams.neverland.gameLoop.service.CharacterService

class CharacterThread(private var service: CharacterService) : Thread() {

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
        while(running) {
            service.render()
        }

    }

}