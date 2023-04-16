package com.hereams.neverland.gameObjects.states

import android.graphics.PointF
import com.hereams.neverland.constant.ACTION_ATTACK
import com.hereams.neverland.constant.ACTION_MOVE
import com.hereams.neverland.gameObjects.GameObject
import kotlin.math.abs
import kotlin.math.atan

class LivingAnimationObjectState(private val view: GameObject) {
    enum class State {
        NO_ACTION, IS_MOVING_RIGHT, IS_MOVING_DOWN, IS_MOVING_FORWARD, IS_MOVING_LEFT,
        IS_ATTACKING_RIGHT, IS_ATTACKING_DOWN, IS_ATTACKING_FORWARD, IS_ATTACKING_LEFT
    }

    private var state: State

    init {
        state = State.NO_ACTION
    }

    fun getState(): State {
        return state
    }

    fun update() {
        var angle: Float
        val velocity: PointF = view.getObjectVelocity()
        val action: String = view.getAction()
        //1 and 3 circle angles
        if ((velocity.x < 0f && velocity.y > 0f) || (velocity.x > 0f && velocity.y < 0f)) {
            angle = atan(abs(velocity.x / velocity.y))
            if (angle <= Math.PI / 4)
                state =
                    if (velocity.y < 0f)
                        State.IS_MOVING_DOWN
                    else
                        State.IS_MOVING_FORWARD
            else
                state =
                    if (velocity.y < 0f)
                        State.IS_MOVING_RIGHT
                    else
                        State.IS_MOVING_LEFT
        } else if (velocity.x == 0f && velocity.y == 0f && action != ACTION_ATTACK)
            state = State.NO_ACTION

        //2 and 4 circle angles
        else {
            angle = atan(velocity.y / velocity.x)
            if (angle <= Math.PI / 4)
                state =
                    if (velocity.x < 0f)
                        State.IS_MOVING_LEFT
                    else
                        State.IS_MOVING_RIGHT
            else
                state =
                    if (velocity.x < 0f)
                        State.IS_MOVING_DOWN
                    else
                        State.IS_MOVING_FORWARD
        }

        if (action == ACTION_ATTACK) {
            val direction = view.getDirection()
            state =
                if (direction == State.IS_MOVING_FORWARD)
                    State.IS_ATTACKING_FORWARD
                else if (direction == State.IS_MOVING_DOWN)
                    State.IS_ATTACKING_DOWN
                else if (direction == State.IS_MOVING_RIGHT)
                    State.IS_ATTACKING_RIGHT
                else
                    State.IS_ATTACKING_LEFT
        }

    }
}