package com.hereams.neverland.gameObjects.states

import com.hereams.neverland.gameObjects.view.component.CharacterView
import kotlin.math.abs
import kotlin.math.atan

class CharacterState(private val view: CharacterView) {
    enum class State {
        NOT_MOVING, IS_MOVING_RIGHT, IS_MOVING_DOWN, IS_MOVING_FORWARD, IS_MOVING_LEFT
    }

    private var state: State

    init {
        state = State.NOT_MOVING
    }

    fun getState(): State {
        return state
    }

    fun update() {
        var angle: Float

        //1 and 3 circle angles
        if ((view.velocity.x < 0f && view.velocity.y > 0f) || (view.velocity.x > 0f && view.velocity.y < 0f)) {
            angle = atan(abs(view.velocity.x / view.velocity.y))
            if (angle <= Math.PI / 4)
                state =
                    if (view.velocity.y < 0f) State.IS_MOVING_DOWN else State.IS_MOVING_FORWARD
            else
                state =
                    if (view.velocity.y < 0f) State.IS_MOVING_RIGHT else State.IS_MOVING_LEFT
        } else if (view.velocity.x == 0f && view.velocity.y == 0f)
            state = State.NOT_MOVING

        //2 and 4 circle angles
        else {
            angle = atan(view.velocity.y / view.velocity.x)
            if (angle <= Math.PI / 4)
                state =
                    if (view.velocity.x < 0f) State.IS_MOVING_LEFT else State.IS_MOVING_RIGHT
            else
                state =
                    if (view.velocity.x < 0f) State.IS_MOVING_DOWN else State.IS_MOVING_FORWARD
        }

    }
}