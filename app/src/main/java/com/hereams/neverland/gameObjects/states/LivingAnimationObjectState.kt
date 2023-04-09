package com.hereams.neverland.gameObjects.states

import android.graphics.PointF
import com.hereams.neverland.gameObjects.GameObject
import kotlin.math.abs
import kotlin.math.atan

class LivingAnimationObjectState(private val view: GameObject) {
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
        val velocity: PointF = view.getObjectVelocity()
        //1 and 3 circle angles
        if ((velocity.x < 0f && velocity.y > 0f) || (velocity.x > 0f && velocity.y < 0f)) {
            angle = atan(abs(velocity.x / velocity.y))
            if (angle <= Math.PI / 4)
                state =
                    if (velocity.y < 0f) State.IS_MOVING_DOWN else State.IS_MOVING_FORWARD
            else
                state =
                    if (velocity.y < 0f) State.IS_MOVING_RIGHT else State.IS_MOVING_LEFT
        } else if (velocity.x == 0f && velocity.y == 0f)
            state = State.NOT_MOVING

        //2 and 4 circle angles
        else {
            angle = atan(velocity.y / velocity.x)
            if (angle <= Math.PI / 4)
                state =
                    if (velocity.x < 0f) State.IS_MOVING_LEFT else State.IS_MOVING_RIGHT
            else
                state =
                    if (velocity.x < 0f) State.IS_MOVING_DOWN else State.IS_MOVING_FORWARD
        }

    }
}