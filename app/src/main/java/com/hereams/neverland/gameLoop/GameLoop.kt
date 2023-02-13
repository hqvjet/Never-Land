package com.hereams.neverland.gameLoop

class GameLoop: Thread() {

    var is_in_game: Boolean = true
    var is_exit: Boolean = false

    override fun run() {
        while(!is_exit) {
            if(is_in_game) {
            }

            else {
                sleep(100)
            }
        }
    }

}