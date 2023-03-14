package com.hereams.neverland.gameLoop.service

import com.hereams.neverland.gameObjects.view.component.CharacterView
import com.hereams.neverland.gameObjects.view.component.DPadView
import kotlin.math.*

class DPadService(private val view: DPadView) {

    private lateinit var character_view: CharacterView
    lateinit var action_handler: Thread
    var holding: Boolean = false
    var is_thread_running: Boolean = false

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
        val temp_x = abs(view.position.x - view.center)
        val temp_y = abs(view.position.y - view.center)
        var degree =
            if (temp_x == 200f || temp_y == 200f) 0f
            else if ((view.position.x > view.center && view.position.y < view.center) || (view.position.x < view.center && view.position.y > view.center))
                atan(temp_x / temp_y)
            else
                atan(temp_y / temp_x)

        var v_x: Float
        var v_y: Float

        action_handler = Thread {
            if (!is_thread_running) {
                is_thread_running = true
                while (holding) {
                    v_y =
                        if ((view.position.x > view.center && view.position.y < view.center) || (view.position.x < view.center && view.position.y > view.center))
                            if (view.position.y > view.center)
                                cos(degree) * character_view.model.getMoveSpeed().toFloat()
                            else
                                -1 * cos(degree) * character_view.model.getMoveSpeed().toFloat()
                        else if (temp_y == 200f)
                            character_view.model.getMoveSpeed().toFloat()
                        else
                            if (view.position.y > view.center)
                                sin(degree) * character_view.model.getMoveSpeed().toFloat()
                            else
                                -1 * sin(degree) * character_view.model.getMoveSpeed().toFloat()

                    v_x =
                        if ((view.position.x > view.center && view.position.y < view.center) || (view.position.x < view.center && view.position.y > view.center))
                            if (view.position.x > view.center)
                                sin(degree) * character_view.model.getMoveSpeed().toFloat()
                            else
                                -1 * sin(degree) * character_view.model.getMoveSpeed().toFloat()
                        else if (temp_x == 200f)
                            character_view.model.getMoveSpeed().toFloat()
                        else
                            if (view.position.x > view.center)
                                cos(degree) * character_view.model.getMoveSpeed().toFloat()
                            else
                                -1 * cos(degree) * character_view.model.getMoveSpeed().toFloat()

                    character_view.service.update(v_x, v_y)
                    Thread.sleep(50)
                }
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