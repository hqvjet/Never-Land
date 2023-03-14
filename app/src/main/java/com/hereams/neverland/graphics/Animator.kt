package com.hereams.neverland.graphics

import android.graphics.Canvas
import android.graphics.PointF
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameObjects.states.CharacterState
import com.hereams.neverland.gameObjects.view.component.CharacterView

class Animator(
    private var position: PointF,
    private val characterMovementSpritesArray: ArrayList<SpritesSheet>
) {

    private var frameIndex = 0
    private var frameWaiter = WAITING_PER_FRAME
    private lateinit var characterForwardMovementSpritesArray: Array<Sprites>
    private lateinit var characterRightMovementSpritesArray: Array<Sprites>
    private lateinit var characterDownMovementSpritesArray: Array<Sprites>
    private lateinit var characterLeftMovementSpritesArray: Array<Sprites>
    private var changed: Boolean = false

    init {
        characterForwardMovementSpritesArray =
            characterMovementSpritesArray[0].getCharacterMovementSpritesArray()
        characterRightMovementSpritesArray =
            characterMovementSpritesArray[1].getCharacterMovementSpritesArray()
        characterDownMovementSpritesArray =
            characterMovementSpritesArray[2].getCharacterMovementSpritesArray()
        characterLeftMovementSpritesArray =
            characterMovementSpritesArray[3].getCharacterMovementSpritesArray()
    }

    fun draw(canvas: Canvas?, view: CharacterView) {
        when (view.getState()) {
            CharacterState.State.NOT_MOVING -> {
                changed = true
                drawFrame(canvas, characterForwardMovementSpritesArray[0])
            }
            CharacterState.State.IS_MOVING_RIGHT -> {
                changed = true
                if (handleFrameWaiter()) {
                    drawFrame(canvas, characterRightMovementSpritesArray[frameIndex])
                }
            }
            CharacterState.State.IS_MOVING_FORWARD -> {
                changed = true
                if (handleFrameWaiter()) {
                    drawFrame(canvas, characterForwardMovementSpritesArray[frameIndex])
                    toggleMovingAnimationSprites()
                }
            }
            CharacterState.State.IS_MOVING_LEFT -> {
                if (handleFrameWaiter()) {
                    changed = true
                    drawFrame(canvas, characterLeftMovementSpritesArray[frameIndex])
                    toggleMovingAnimationSprites()
                }
            }
            CharacterState.State.IS_MOVING_DOWN -> {
                changed = true
                if (handleFrameWaiter()) {
                    drawFrame(canvas, characterDownMovementSpritesArray[frameIndex])
                    toggleMovingAnimationSprites()
                }
            }
        }
    }

    private fun drawFrame(canvas: Canvas?, sprites: Sprites) {
        sprites.draw(canvas, position)
    }

    private fun handleFrameWaiter(): Boolean {
        if (frameWaiter == WAITING_PER_FRAME || changed) {
            frameWaiter = 0
            return true
        }
        ++frameWaiter
        return false
    }

    private fun toggleMovingAnimationSprites() {
        if (frameIndex == MAX_MOVEMENT_SPRITES_SIZE - 1)
            frameIndex = 0
        ++frameIndex
    }

    fun update(value: PointF) {
        position = value
    }

}