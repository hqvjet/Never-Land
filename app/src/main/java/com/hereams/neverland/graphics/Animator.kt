package com.hereams.neverland.graphics

import android.graphics.Canvas
import android.graphics.PointF
import com.hereams.neverland.gameObjects.states.CharacterState
import com.hereams.neverland.gameObjects.view.component.CharacterView

class Animator(private var position: PointF,private val characterSpritesArray: Array<Sprites>) {

    private var frameIndex = 1

    fun draw(canvas: Canvas?, view: CharacterView) {
        when (view.getState()) {
            CharacterState.State.NOT_MOVING -> {
                drawFrame(canvas, characterSpritesArray[0])
            }
            CharacterState.State.STARED_MOVING -> {
                drawFrame(canvas, characterSpritesArray[1])
            }
            CharacterState.State.IS_MOVING -> {
                drawFrame(canvas, characterSpritesArray[frameIndex])
                toggleMovingAnimationSprites()
            }
        }
    }

    fun drawFrame(canvas: Canvas?, sprites: Sprites) {
        sprites.draw(canvas, position)
    }

    fun toggleMovingAnimationSprites() {
        if (frameIndex == 6)
            frameIndex = 1
        ++frameIndex
    }

    fun update(value: PointF) {
        position = value
    }

}