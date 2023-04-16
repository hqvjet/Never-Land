package com.hereams.neverland.graphics

import android.graphics.Canvas
import android.graphics.PointF
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameObjects.GameObject
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState
import com.hereams.neverland.gameObjects.view.component.EnemyView

class Animator(
    private val object_sprites_array: ArrayList<SpritesSheet>
) {

    private var frameIndex = 0
    private lateinit var objectForwardMovementSpritesArray: Array<Sprites>
    private lateinit var objectRightMovementSpritesArray: Array<Sprites>
    private lateinit var objectDownMovementSpritesArray: Array<Sprites>
    private lateinit var objectLeftMovementSpritesArray: Array<Sprites>

    private lateinit var objectForwardAttackSpritesArray: Array<Sprites>
    private lateinit var objectRightAttackSpritesArray: Array<Sprites>
    private lateinit var objectDownAttackSpritesArray: Array<Sprites>
    private lateinit var objectLeftAttackSpritesArray: Array<Sprites>

    private var FPS: Float = 0f
    private var count: Int = 0
    private var MOVE_UPDATE_DELAY: Int = 5
    private var ATTACK_UPDATE_DELAY: Int = 5

    init {
        objectForwardMovementSpritesArray =
            object_sprites_array[0].getCharacterSpritesArray()
        objectRightMovementSpritesArray =
            object_sprites_array[1].getCharacterSpritesArray()
        objectDownMovementSpritesArray =
            object_sprites_array[2].getCharacterSpritesArray()
        objectLeftMovementSpritesArray =
            object_sprites_array[3].getCharacterSpritesArray()
        objectForwardAttackSpritesArray =
            object_sprites_array[4].getCharacterSpritesArray()
        objectRightAttackSpritesArray =
            object_sprites_array[5].getCharacterSpritesArray()
        objectDownAttackSpritesArray =
            object_sprites_array[6].getCharacterSpritesArray()
        objectLeftAttackSpritesArray =
            object_sprites_array[7].getCharacterSpritesArray()
    }

    fun update(fps: Float) {
        FPS = fps
    }

    fun draw(canvas: Canvas?, gameDisplay: GameDisplay, drawn_object: GameObject) {

        ++count
        val direction = drawn_object.getDirection()
        when (drawn_object.getState()) {
            LivingAnimationObjectState.State.NO_ACTION -> {
                drawFrame(
                    canvas,
                    if (direction == LivingAnimationObjectState.State.IS_MOVING_FORWARD)
                        objectForwardMovementSpritesArray[0]
                    else if (direction == LivingAnimationObjectState.State.IS_MOVING_DOWN)
                        objectDownMovementSpritesArray[0]
                    else if (direction == LivingAnimationObjectState.State.IS_MOVING_RIGHT)
                        objectRightMovementSpritesArray[0]
                    else
                        objectLeftMovementSpritesArray[0],
                    gameDisplay,
                    drawn_object
                )
            }

            //moving
            LivingAnimationObjectState.State.IS_MOVING_RIGHT -> {
                drawn_object.setDirection(LivingAnimationObjectState.State.IS_MOVING_RIGHT)
                drawFrame(
                    canvas, objectRightMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }
            LivingAnimationObjectState.State.IS_MOVING_FORWARD -> {
                drawn_object.setDirection(LivingAnimationObjectState.State.IS_MOVING_FORWARD)
                drawFrame(
                    canvas, objectForwardMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }
            LivingAnimationObjectState.State.IS_MOVING_LEFT -> {
                drawn_object.setDirection(LivingAnimationObjectState.State.IS_MOVING_LEFT)
                drawFrame(
                    canvas, objectLeftMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }
            LivingAnimationObjectState.State.IS_MOVING_DOWN -> {
                drawn_object.setDirection(LivingAnimationObjectState.State.IS_MOVING_DOWN)
                drawFrame(
                    canvas, objectDownMovementSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                toggleMovingAnimationSprites()

            }

            //attacking
            LivingAnimationObjectState.State.IS_ATTACKING_RIGHT -> {
                drawFrame(
                    canvas, objectRightAttackSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                handleAttackAnimationSprites(drawn_object)

            }
            LivingAnimationObjectState.State.IS_ATTACKING_FORWARD -> {
                drawFrame(
                    canvas, objectForwardAttackSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                handleAttackAnimationSprites(drawn_object)

            }
            LivingAnimationObjectState.State.IS_ATTACKING_LEFT -> {
                drawFrame(
                    canvas, objectLeftAttackSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                handleAttackAnimationSprites(drawn_object)

            }
            LivingAnimationObjectState.State.IS_ATTACKING_DOWN -> {
                drawFrame(
                    canvas, objectDownAttackSpritesArray[frameIndex],
                    gameDisplay,
                    drawn_object
                )
                handleAttackAnimationSprites(drawn_object)

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
        if (count >= MOVE_UPDATE_DELAY) {
            if (frameIndex == MAX_MOVEMENT_SPRITES_SIZE_OF_CHARACTER - 1)
                frameIndex = -1
            ++frameIndex
            count = 0
        }
    }

    private fun handleAttackAnimationSprites(drawn_object: GameObject) {
        if (count >= ATTACK_UPDATE_DELAY) {

            //done 1 attack action
            if (frameIndex >= MAX_ATTACK_SPRITES_SIZE_OF_CHARACTER - 1) {
//                drawn_object.setIsAttacking(false)
//                drawn_object.setReadyToAttack(true)
                drawn_object.setAction(ACTION_MOVE)
                frameIndex = -1
            }

            ++frameIndex
            count = 0
        }

    }

}