package com.hereams.neverland.gameLoop.controller.thread

import com.hereams.neverland.gameLoop.service.DPadService

class DPadThread(private val service: DPadService) : Thread() {

    private var running: Boolean = true
    private var sleepTime: Long = 0

    fun setSleepTime(value: Long) {
        sleepTime = value
    }

    override fun run() {
        while (running) {
            service.render()
            sleep(sleepTime)
        }

    }

    fun turnOn() {
        running = true
        start()
    }

    fun turnOff() {
        running = false
        join()
    }
}