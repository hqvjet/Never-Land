package com.hereams.neverland.gameLoop.service

import com.hereams.neverland.constant.SPRITES_SIZE
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
        println(view.state.getState().toString())
        teleport(view.velocity.x, view.velocity.y)
    }

    fun teleport(x: Float, y: Float) {

        if (view.position.x + x >= 0 && view.position.x <= view.width - SPRITES_SIZE)
            view.position.x += x
        else if (view.position.x > view.width - SPRITES_SIZE)
            view.position.x = (view.width - SPRITES_SIZE).toFloat()
        else
            view.position.x = 0f
        if (view.position.y + y >= 0 && view.position.y <= view.height - SPRITES_SIZE)
            view.position.y += y
        else if (view.position.y > view.height - SPRITES_SIZE)
            view.position.y = (view.height - SPRITES_SIZE).toFloat()
        else
            view.position.y = 0f
        view.animator.update(view.position)
    }

    fun render() {
        view.canvas = view.sf_holder.lockCanvas()
        view.onDraw(view.canvas!!)
        view.sf_holder.unlockCanvasAndPost(view.canvas)
        view.invalidate()
    }

}