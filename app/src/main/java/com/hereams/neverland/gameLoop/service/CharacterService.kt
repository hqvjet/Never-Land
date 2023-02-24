package com.hereams.neverland.gameLoop.service

import com.hereams.neverland.gameObjects.view.CharacterView
import com.hereams.neverland.gameObjects.view.DPadView
import kotlin.math.acos
import kotlin.math.cos
import kotlin.math.pow
import kotlin.math.sqrt

class CharacterService(private val view: CharacterView) {

    /**
     * Update function for updating velocity of character, then it calls teleport function
     * @param x, y
     * @return void
     */
    fun update(x: Float, y: Float) {
        view.velocity.x = x
        view.velocity.y = y
        teleport(view.velocity.x, view.velocity.y)
    }

    fun teleport(x: Float, y: Float) {
        view.position.x += x
        view.position.y += y
        view.invalidate()
    }

    fun render() {
        view.canvas = view.sf_holder.lockCanvas()
        view.draw(view.canvas!!)
        view.sf_holder.unlockCanvasAndPost(view.canvas)
    }

}