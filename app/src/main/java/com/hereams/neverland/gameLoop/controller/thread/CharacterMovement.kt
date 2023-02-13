package com.hereams.neverland.gameLoop.controller.thread

import com.hereams.neverland.gameObjects.view.CharacterView

class CharacterMovement(private val character: CharacterView): Thread() {
    private var permission: Boolean = false

    fun setRunning(value: Boolean) {
        permission = value
    }

    override fun run() {
        while (permission) {
            character.move(character.model.getMoveSpeed().toInt(), character.model.getMoveSpeed().toInt())
//            try {
//                Thread.sleep(10)
//            } catch (e: InterruptedException) {
//                e.printStackTrace()
//            }
        }
    }

}