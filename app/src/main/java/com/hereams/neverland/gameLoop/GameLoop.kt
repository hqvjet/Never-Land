package com.hereams.neverland.gameLoop

import com.hereams.neverland.constant.FPS
import com.hereams.neverland.gameObjects.view.component.CharacterView
import com.hereams.neverland.gameObjects.view.component.DPadView

class GameLoop(
    character: CharacterView,
    dpad: DPadView
) : Thread() {

    var is_in_game: Boolean = true
    var is_exit: Boolean = false

    init {
        character.move_thread.setSleepTime(FPS)
        dpad.thread.setSleepTime(FPS)
    }

    override fun run() {
        while (!is_exit) {
            if (is_in_game) {
            } else {
            }
        }
    }

}