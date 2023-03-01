package com.hereams.neverland.gameObjects.states

import com.hereams.neverland.gameObjects.view.component.CharacterView

class CharacterState(private val view: CharacterView) {
    enum class State {
        NOT_MOVING, STARED_MOVING, IS_MOVING
    }
    private var state: State

    init {
        state = State.NOT_MOVING
    }

    fun getState(): State {
        return state
    }

    fun update() {
        when (state) {
            State.NOT_MOVING -> if (view.velocity.x != 0f || view.velocity.y != 0f)
                state = State.STARED_MOVING
            State.STARED_MOVING -> if (view.velocity.x != 0f || view.velocity.y != 0f)
                state = State.IS_MOVING
            State.IS_MOVING -> if (view.velocity.x == 0f && view.velocity.y == 0f)
                state = State.NOT_MOVING
        }
    }
}