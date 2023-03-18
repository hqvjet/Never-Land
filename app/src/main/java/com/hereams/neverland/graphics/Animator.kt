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
    private lateinit var characterForwardMovementSpritesArray: Array<Sprites>
    private lateinit var characterRightMovementSpritesArray: Array<Sprites>
    private lateinit var characterDownMovementSpritesArray: Array<Sprites>
    private lateinit var characterLeftMovementSpritesArray: Array<Sprites>
    private var direction: Int = DIRECTION_FORWARD

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
                drawFrame(
                    canvas,
                    if (direction == DIRECTION_FORWARD) characterForwardMovementSpritesArray[0]
                    else if (direction == DIRECTION_DOWN) characterDownMovementSpritesArray[0]
                    else if (direction == DIRECTION_RIGHT) characterRightMovementSpritesArray[0]
                    else characterLeftMovementSpritesArray[0]
                )
            }
            CharacterState.State.IS_MOVING_RIGHT -> {
                direction = DIRECTION_RIGHT
                drawFrame(canvas, characterRightMovementSpritesArray[frameIndex])
                toggleMovingAnimationSprites()

            }
            CharacterState.State.IS_MOVING_FORWARD -> {
                direction = DIRECTION_FORWARD
                drawFrame(canvas, characterForwardMovementSpritesArray[frameIndex])
                toggleMovingAnimationSprites()

            }
            CharacterState.State.IS_MOVING_LEFT -> {
                direction = DIRECTION_LEFT
                drawFrame(canvas, characterLeftMovementSpritesArray[frameIndex])
                toggleMovingAnimationSprites()

            }
            CharacterState.State.IS_MOVING_DOWN -> {
                direction = DIRECTION_DOWN
                drawFrame(canvas, characterDownMovementSpritesArray[frameIndex])
                toggleMovingAnimationSprites()

            }
        }
    }

    private fun drawFrame(canvas: Canvas?, sprites: Sprites) {
        sprites.draw(canvas, position)
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