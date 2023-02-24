package com.hereams.neverland.gameLoop.controller.thread

import android.graphics.Canvas
import com.hereams.neverland.gameLoop.service.CharacterService
import com.hereams.neverland.gameObjects.view.CharacterView
import com.hereams.neverland.gameObjects.view.DPadView

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