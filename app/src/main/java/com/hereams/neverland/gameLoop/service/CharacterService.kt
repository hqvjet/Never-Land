package com.hereams.neverland.gameLoop.service

import com.hereams.neverland.gameObjects.view.component.CharacterView

class CharacterService(private val view: CharacterView) {

    /**
     * Update function for updating velocity of character, then it calls teleport function
     * @param x, y
     * @return void
     */
    fun update(x: Float, y: Float) {
        view.velocity.x = x
        view.velocity.y = y
        view.state.update()
        teleport(view.velocity.x, view.velocity.y)
    }

    fun teleport(x: Float, y: Float) {
        view.position.x += x
        view.position.y += y
        view.animator.update(view.position)
    }

    fun render() {
        view.canvas = view.sf_holder.lockCanvas()
        view.onDraw(view.canvas!!)
        view.sf_holder.unlockCanvasAndPost(view.canvas)
        view.invalidate()
    }

}