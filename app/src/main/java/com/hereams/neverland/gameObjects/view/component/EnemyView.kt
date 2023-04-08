package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import com.hereams.neverland.R
import com.hereams.neverland.constant.DIRECTION_LEFT
import com.hereams.neverland.constant.ENEMY
import com.hereams.neverland.gameLoop.controller.thread.AttackController
import com.hereams.neverland.gameObjects.model.Enemy
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState
import com.hereams.neverland.graphics.Animator
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.SpritesSheet


class EnemyView(
    context: Context,
    enemy_id: Int,
    private val target: CharacterView,
    position: PointF,
    radius: Float
) :
    Circle(context, Color.RED, position, radius) {

    private lateinit var animator: Animator
    private lateinit var characterForwardMovementSpritesSheet: SpritesSheet
    private lateinit var characterRightMovementSpritesSheet: SpritesSheet
    private lateinit var characterLeftMovementSpritesSheet: SpritesSheet
    private lateinit var characterDownMovementSpritesSheet: SpritesSheet
    private lateinit var movementSpritesSheetArray: ArrayList<SpritesSheet>

    lateinit var state: LivingAnimationObjectState
    lateinit var model: Enemy
    lateinit var attack_controller: AttackController
    var ready_to_attack: Boolean = true
    var count: Int = 0

    private lateinit var MAX_SPEED: Number
    private var FPS: Float = 0f

    init {
        characterForwardMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_front_movement, null, ENEMY)
        characterRightMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_side_movement, null, ENEMY)
        characterDownMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_back_movement, null, ENEMY)
        characterLeftMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_side_movement, DIRECTION_LEFT, ENEMY)
        movementSpritesSheetArray = ArrayList()
        movementSpritesSheetArray.add(characterForwardMovementSpritesSheet)
        movementSpritesSheetArray.add(characterRightMovementSpritesSheet)
        movementSpritesSheetArray.add(characterDownMovementSpritesSheet)
        movementSpritesSheetArray.add(characterLeftMovementSpritesSheet)

        animator = Animator(movementSpritesSheetArray)

        state = LivingAnimationObjectState(this)

        model = Enemy(1, enemy_id)
        attack_controller = AttackController(model.getAttackSpeed().toFloat(), this)

        MAX_SPEED = model.getEnemyMoveSpeed()
    }

    override fun draw(canvas: Canvas?, gameDisplay: GameDisplay) {
        animator.draw(canvas, gameDisplay, this)
    }

    override fun update(fps: Float) {
        FPS = fps
//        println(fps)
        if (!ready_to_attack)
            ++count
        if (count >= FPS / model.getAttackSpeed().toFloat()) {
            count = 0
            ready_to_attack = true
        }
// =========================================================================================// Calculate vector from enemy to player (in x and y)
        // =========================================================================================
        //   Update velocity of the enemy so that the velocity is in the direction of the player
        // =========================================================================================
        // Calculate vector from enemy to player (in x and y)
        val distanceToPlayerX: Float = target.getObjectPosition().x - position.x
        val distanceToPlayerY: Float = target.getObjectPosition().y - position.y

        // Calculate (absolute) distance between enemy (this) and player
        val distanceToPlayer = getDistanceBetweenObjects(this, target)

        // Calculate direction from enemy to player
        val directionX = distanceToPlayerX / distanceToPlayer
        val directionY = distanceToPlayerY / distanceToPlayer

        // Set velocity in the direction to the player
        if (distanceToPlayer > 0) { // Avoid division by zero
            velocity.x = directionX * MAX_SPEED.toFloat()
            velocity.y = directionY * MAX_SPEED.toFloat()
        } else {
            velocity.x = 0f
            velocity.y = 0f
        }

        // =========================================================================================
        //   Update position of the enemy
        // =========================================================================================

        // =========================================================================================
        //   Update position of the enemy
        // =========================================================================================
        position.x += velocity.x
        position.y += velocity.y
    }

    override fun getState(): LivingAnimationObjectState.State {
        return state.getState()
    }


}