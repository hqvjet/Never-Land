package com.hereams.neverland.gameLoop.controller.thread

import com.hereams.neverland.gameLoop.service.DPadService

class DPadThread(private val service: DPadService) : Thread() {

    private var running: Boolean = true

    override fun run() {
        while (running)
            service.render()
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