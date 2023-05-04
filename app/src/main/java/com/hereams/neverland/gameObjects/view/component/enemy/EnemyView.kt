package com.hereams.neverland.gameObjects.view.component.enemy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.graphics.Rect
import com.hereams.neverland.R
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameObjects.model.Enemy
import com.hereams.neverland.gameObjects.model.Inventory
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState
import com.hereams.neverland.gameObjects.view.component.character.CharacterView
import com.hereams.neverland.gameObjects.view.component.Circle
import com.hereams.neverland.gameObjects.view.component.map.Tile
import com.hereams.neverland.graphics.Animator
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.SpritesSheet
import java.lang.Float.max


abstract class EnemyView(
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
    private lateinit var characterForwardAttackSpritesSheet: SpritesSheet
    private lateinit var characterRightAttackSpritesSheet: SpritesSheet
    private lateinit var characterLeftAttackSpritesSheet: SpritesSheet
    private lateinit var characterDownAttackSpritesSheet: SpritesSheet
    private lateinit var movementSpritesSheetArray: ArrayList<SpritesSheet>
    private lateinit var hp_bar: EnemyBar

    lateinit var state: LivingAnimationObjectState
    lateinit var model: Enemy
    lateinit var MAX_HP: Number
    var count: Int = 0

    private lateinit var MAX_SPEED: Number
    private var FPS: Float = 0f

    init {
        characterForwardMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_front_movement, null, MOVE_SPRITE, ENEMY)
        characterRightMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_side_movement, null, MOVE_SPRITE, ENEMY)
        characterDownMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_back_movement, null, MOVE_SPRITE, ENEMY)
        characterLeftMovementSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_side_movement,
                DIRECTION_LEFT,
                MOVE_SPRITE,
                ENEMY
            )

        characterForwardAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_front_consecutive_slash,
                null,
                ATTACK_SPRITE,
                ENEMY
            )
        characterRightAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_side_consecutive_slash,
                null,
                ATTACK_SPRITE,
                ENEMY
            )
        characterDownAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_back_consecutive_slash,
                null,
                ATTACK_SPRITE,
                ENEMY
            )
        characterLeftAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_side_consecutive_slash,
                DIRECTION_LEFT,
                ATTACK_SPRITE,
                ENEMY
            )

        movementSpritesSheetArray = ArrayList()
        movementSpritesSheetArray.add(characterForwardMovementSpritesSheet)
        movementSpritesSheetArray.add(characterRightMovementSpritesSheet)
        movementSpritesSheetArray.add(characterDownMovementSpritesSheet)
        movementSpritesSheetArray.add(characterLeftMovementSpritesSheet)
        movementSpritesSheetArray.add(characterForwardAttackSpritesSheet)
        movementSpritesSheetArray.add(characterRightAttackSpritesSheet)
        movementSpritesSheetArray.add(characterDownAttackSpritesSheet)
        movementSpritesSheetArray.add(characterLeftAttackSpritesSheet)

        animator = Animator(movementSpritesSheetArray)

        state = LivingAnimationObjectState(this)

        model = Enemy(1, enemy_id)

        MAX_HP = model.getEnemyHp()

        MAX_SPEED = model.getEnemyMoveSpeed()

        hp_bar = EnemyBar(context, this)
    }

    override fun draw(canvas: Canvas?, gameDisplay: GameDisplay) {

        hp_bar.draw(canvas, gameDisplay)

        animator.draw(canvas, gameDisplay, this)
    }

    override fun update(fps: Float, obstacle_list: MutableList<Tile>) {
        FPS = fps
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
        if (action == ACTION_ATTACK) {
            velocity.x = 0f
            velocity.y = 0f
        } else if (distanceToPlayer > 0) { // Avoid division by zero
            velocity.x = directionX * MAX_SPEED.toFloat()
            velocity.y = directionY * MAX_SPEED.toFloat()
        } else {
            velocity.x = 0f
            velocity.y = 0f
        }

        // =========================================================================================
        //   Update position of the enemy
        // =========================================================================================
        handlingIsBlockedByObstacle(obstacle_list)

        state.update()
        animator.update(fps)
    }

    override fun getState(): LivingAnimationObjectState.State {
        return state.getState()
    }

    fun attack(target: CharacterView) {
        action = ACTION_ATTACK
        state.update()
        target.isDamaged(model.getEnemyAttack().toInt())
    }

    fun move() {
        if (!is_attacking) {
            action = ACTION_MOVE
            state.update()
        }
    }

    fun isDamaged(damage: Int) {
        model.setEnemyHp(max(0f, (model.getEnemyHp().toInt() - damage).toFloat()))
        hp_bar.update()
    }

    /**
     * Use for getting drop item when enemy died
     */
    abstract fun drop(character_inventory: Inventory)

    /**
     *  Use for gaining EXP when enemy died
     */
    abstract fun expGain(): Int

    /**
     *  Use for gaining gold when enemy died
     */
    abstract fun goldGain(): Int

}