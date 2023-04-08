package com.hereams.neverland.graphics

import android.graphics.Canvas
import android.graphics.PointF
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameObjects.GameObject
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState

class Animator(
    private val object_movement_sprites_array: ArrayList<SpritesSheet>
) {

    private var frameIndex = 0
    private lateinit var characterForwardMovementSpritesArray: Array<Sprites>
    private lateinit var characterRightMovementSpritesArray: Array<Sprites>
    private lateinit var characterDownMovementSpritesArray: Array<Sprites>
    private lateinit var characterLeftMovementSpritesArray: Array<Sprites>
    private var direction: Int = DIRECTION_FORWARD

    init {
        characterForwardMovementSpritesArray =
            object_movement_sprites_array[0].getCharacterMovementSpritesArray()
        characterRightMovementSpritesArray =
            object_movement_sprites_array[1].getCharacterMovementSpritesArray()
        characterDownMovementSpritesArray =
            object_movement_sprites_array[2].getCharacterMovementSpritesArray()
        characterLeftMovementSpritesArray =
            object_movement_sprites_array[3].getCharacterMovementSpritesArray()
    }

    fun draw(canvas: Canvas?, gameDisplay: GameDisplay, drawn_object: GameObject) {
        when (drawn_object.getState()) {
            LivingAnimationObjectState.State.NOT_MOVING -> {
                drawFrame(
                    canvas,
                    if (direction == DIRECTION_FORWARD) characterForwardMovementSpritesArray[0]
                    else if (direction == DIRECTION_DOWN) characterDownMovementSpritesArray[0]
                    else if (direction == DIRECTION_RIGHT) characterRightMovementSpritesArray[0]
                    else characterLeftMovementSpritesArray[0],
                    gameDisplay,
                    drawn_object
                )
            }
            LivingAnimationObjectState.State.IS_MOVING_RIGHT -> {
                direction = DIRECTION_RIGHT
                drawFrame(
                    canvas, characterRightMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }
            LivingAnimationObjectState.State.IS_MOVING_FORWARD -> {
                direction = DIRECTION_FORWARD
                drawFrame(
                    canvas, characterForwardMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }
            LivingAnimationObjectState.State.IS_MOVING_LEFT -> {
                direction = DIRECTION_LEFT
                drawFrame(
                    canvas, characterLeftMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }
            LivingAnimationObjectState.State.IS_MOVING_DOWN -> {
                direction = DIRECTION_DOWN
                drawFrame(
                    canvas, characterDownMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }
        }
    }

    /**
     * This function is currently created for character only, for more object using, please update more
     */
    private fun drawFrame(
        canvas: Canvas?,
        sprites: Sprites,
        gameDisplay: GameDisplay,
        drawn_object: GameObject
    ) {
        sprites.draw(
            canvas, PointF(
                gameDisplay.gameToDisplayCoordinatesX(drawn_object.getObjectPosition().x - SPRITES_SIZE / 2f),
                gameDisplay.gameToDisplayCoordinatesY(drawn_object.getObjectPosition().y - SPRITES_SIZE / 2f)
            )
        )
    }

    private fun toggleMovingAnimationSprites() {
        if (frameIndex == MAX_MOVEMENT_SPRITES_SIZE_OF_CHARACTER - 1)
            frameIndex = 0
        ++frameIndex
    }

}