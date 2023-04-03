package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import com.hereams.neverland.R
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameObjects.model.Character
import com.hereams.neverland.gameObjects.model.Inventory
import com.hereams.neverland.gameObjects.model.Option
import com.hereams.neverland.gameObjects.model.Weapon
import com.hereams.neverland.gameObjects.states.CharacterState
import com.hereams.neverland.graphics.Animator
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.SpritesSheet

class CharacterView(
    context: Context, position: PointF,
    radius: Float,
    val d_pad: DPadView
) : Circle(context, Color.RED, position, radius) {

    lateinit var canvas: Canvas
    private lateinit var characterForwardMovementSpritesSheet: SpritesSheet
    private lateinit var characterRightMovementSpritesSheet: SpritesSheet
    private lateinit var characterLeftMovementSpritesSheet: SpritesSheet
    private lateinit var characterDownMovementSpritesSheet: SpritesSheet
    private lateinit var movementSpritesSheetArray: ArrayList<SpritesSheet>
    lateinit var animator: Animator
    lateinit var state: CharacterState
    private lateinit var MAX_SPEED: Number

    lateinit var model: Character

    init {

        characterForwardMovementSpritesSheet =
            SpritesSheet(position, context, R.drawable.front_movement, DIRECTION_FORWARD)
        characterRightMovementSpritesSheet =
            SpritesSheet(position, context, R.drawable.side_movement, DIRECTION_RIGHT)
        characterDownMovementSpritesSheet =
            SpritesSheet(position, context, R.drawable.back_movement, DIRECTION_DOWN)
        characterLeftMovementSpritesSheet =
            SpritesSheet(position, context, R.drawable.side_movement, DIRECTION_LEFT)
        movementSpritesSheetArray = ArrayList()
        movementSpritesSheetArray.add(characterForwardMovementSpritesSheet)
        movementSpritesSheetArray.add(characterRightMovementSpritesSheet)
        movementSpritesSheetArray.add(characterDownMovementSpritesSheet)
        movementSpritesSheetArray.add(characterLeftMovementSpritesSheet)

        animator = Animator(movementSpritesSheetArray)
        state = CharacterState(this)
        model = Character(
            "Jack", 1, 1, KNIGHT, 1, 1,
            1, 1, 1, 1, 1,
            Weapon(STEEL_SWORD, 1),
            Inventory(10, 1000),
            Option(EN, "jacky", "1231233")
        )

        MAX_SPEED = model.getMoveSpeed()

    }

    fun getState(): CharacterState.State {
        return state.getState()
    }

    override fun update() {

        // Update velocity based on actuator of d_pad
        velocity.x = d_pad.getActuatorX() * MAX_SPEED.toFloat()
        velocity.y = d_pad.getActuatorY() * MAX_SPEED.toFloat()

        // Update position
        position.x += velocity.x
        position.y += velocity.y

        // Update direction
        if (velocity.x !== 0f || velocity.y !== 0f) {
            // Normalize velocity to get direction (unit vector of velocity)
            val distance: Float = Utils().getDistanceBetweenPoints(0f, 0f, velocity.x, velocity.y)
            direction.x = velocity.x / distance
            direction.y = velocity.y / distance
            println(distance)
        }

//        println("${velocity.x} ${velocity.y}")

        state.update()
    }

    override fun draw(canvas: Canvas?, gameDisplay: GameDisplay) {
        animator.draw(canvas, gameDisplay, this)
//        healthBar.draw(canvas, gameDisplay)
    }

}