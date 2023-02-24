package com.hereams.neverland.gameLoop.service

import com.hereams.neverland.gameObjects.view.CharacterView
import com.hereams.neverland.gameObjects.view.DPadView
import kotlin.math.*

class DPadService(private val view: DPadView) {

    private lateinit var character_view: CharacterView
    lateinit var action_handler: Thread
    var holding: Boolean = false

    fun update(x: Float, y: Float) {

        //dpad handler
        if ((x - view.center).pow(2) + (y - view.center).pow(2) <= view.outerRadius.pow(2)) {
            view.position.x = x
            view.position.y = y
        } else {
            view.position.x =
                cos(acos((view.center - x) / sqrt((view.center - x).pow(2) + (view.center - y).pow(2)))) * view.outerRadius
            view.position.y = sqrt(view.outerRadius.pow(2) - view.position.x.pow(2))
            view.position.x = view.center - view.position.x
            view.position.y = view.center - view.position.y
            if (y > view.center)
                view.position.y = view.position.y + (view.center - view.position.y) * 2
        }

        //character handler: get x, y speed

        val degree = atan(view.position.y / view.position.x)
        var v_x: Float
        var v_y: Float
        action_handler = Thread {
            while (holding) {
                v_x = if (x > view.center)
                    cos(degree) * character_view.model.getMoveSpeed().toFloat()
                else
                    -1 * cos(degree) * character_view.model.getMoveSpeed().toFloat()
                v_y = if (y > view.center)
                    sin(degree) * character_view.model.getMoveSpeed().toFloat()
                else
                    -1 * sin(degree) * character_view.model.getMoveSpeed().toFloat()
                character_view.service.update(v_x, v_y)
                Thread.sleep(50)
//        character_view.service.update(1f, 1f)
            }
        }
        action_handler.start()

    }

    fun render() {
        view.canvas = view.surfaceHolder.lockCanvas()
        view.draw(view.canvas!!)
        view.surfaceHolder.unlockCanvasAndPost(view.canvas)
        view.invalidate()
    }

    fun addCharacter(characterView: CharacterView) {
        character_view = characterView
    }

}