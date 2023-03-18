package com.hereams.neverland.gameLoop.controller.thread

import com.hereams.neverland.gameObjects.view.component.CharacterView

class CharacterMotionThread(private val view: CharacterView) : Thread() {

    private var running: Boolean = false
    private var sleepTime: Long = 0

    fun turnOn() {
        running = true
        start()
    }

    fun turnOff() {
        running = false
        join()
    }

    fun setSleepTime(value: Long) {
        sleepTime = value
    }

    override fun run() {
        while (running){
            view.service.render()
            sleep(sleepTime)
        }

    }

}
