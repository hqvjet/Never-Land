package com.hereams.neverland.gameObjects.view.component.character

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.graphics.Rect
import com.hereams.neverland.R
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameObjects.model.*
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState
import com.hereams.neverland.gameObjects.view.component.Circle
import com.hereams.neverland.gameObjects.view.component.enemy.EnemyView
import com.hereams.neverland.gameObjects.view.component.map.Tile
import com.hereams.neverland.graphics.Animator
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.graphics.SpritesSheet
import kotlin.math.max

class CharacterView(
    context: Context, position: PointF,
    radius: Float,
    val d_pad: DPadView,
    val inventory: Inventory
) : Circle(context, Color.RED, position, radius) {

    lateinit var canvas: Canvas
    private lateinit var characterForwardMovementSpritesSheet: SpritesSheet
    private lateinit var characterRightMovementSpritesSheet: SpritesSheet
    private lateinit var characterLeftMovementSpritesSheet: SpritesSheet
    private lateinit var characterDownMovementSpritesSheet: SpritesSheet
    private lateinit var characterForwardAttackSpritesSheet: SpritesSheet
    private lateinit var characterRightAttackSpritesSheet: SpritesSheet
    private lateinit var characterLeftAttackSpritesSheet: SpritesSheet
    private lateinit var characterDownAttackSpritesSheet: SpritesSheet
    private lateinit var movementSpritesSheetArray: ArrayList<SpritesSheet>
    lateinit var animator: Animator
    lateinit var state: LivingAnimationObjectState
    private lateinit var MAX_SPEED: Number
    private lateinit var MAX_HP: Number
    private var count = 0

    lateinit var model: Character

    init {

        characterForwardMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_front_movement, null, MOVE_SPRITE, CHARACTER)
        characterRightMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_side_movement, null, MOVE_SPRITE, CHARACTER)
        characterDownMovementSpritesSheet =
            SpritesSheet(context, R.drawable.character_back_movement, null, MOVE_SPRITE, CHARACTER)
        characterLeftMovementSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_side_movement,
                DIRECTION_LEFT,
                MOVE_SPRITE,
                CHARACTER
            )

        characterForwardAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_front_consecutive_slash,
                null,
                ATTACK_SPRITE,
                CHARACTER
            )
        characterRightAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_side_consecutive_slash,
                null,
                ATTACK_SPRITE,
                CHARACTER
            )
        characterDownAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_back_consecutive_slash,
                null,
                ATTACK_SPRITE,
                CHARACTER
            )
        characterLeftAttackSpritesSheet =
            SpritesSheet(
                context,
                R.drawable.character_side_consecutive_slash,
                DIRECTION_LEFT,
                ATTACK_SPRITE,
                CHARACTER
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
        model = Character(
            "Jack", 1, 0, KNIGHT, 1, 1,
            1, 1, 1, 1, 1,
            Weapon(STEEL_SWORD, 1),
            inventory,
            Option(EN, "jacky", "1231233")
        )

        MAX_SPEED = model.getMoveSpeed()
        MAX_HP = model.getPlayerHp()
    }

    override fun getState(): LivingAnimationObjectState.State {
        return state.getState()
    }

    fun getMaxCharacterHP(): Int {
        return MAX_HP.toInt()
    }

    override fun update(fps: Float, obstacle_list: MutableList<Tile>) {
        if (!ready_to_attack)
            ++count
        if (count >= fps / model.getAttackSpeed().toFloat()) {
            count = 0
            ready_to_attack = true
        }

        // Update velocity based on actuator of d_pad
        if (action != ACTION_ATTACK) {
            velocity.x = d_pad.getActuatorX() * MAX_SPEED.toFloat()
            velocity.y = d_pad.getActuatorY() * MAX_SPEED.toFloat()
        } else {
            velocity.x = 0f
            velocity.y = 0f
        }

        handlingIsBlockedByObstacle(obstacle_list)
        println(obstacle_list.size)

            // Update direction
            if (velocity.x !== 0f || velocity.y !== 0f) {
                // Normalize velocity to get direction (unit vector of velocity)
                val distance: Float =
                    Utils().getDistanceBetweenPoints(0f, 0f, velocity.x, velocity.y)
            }

            state.update()
            animator.update(fps)

    }

    override fun draw(canvas: Canvas?, gameDisplay: GameDisplay) {
        animator.draw(canvas, gameDisplay, this)
    }

    fun isDamaged(damaged: Int) {
        model.setPlayerHp(max(0, model.getPlayerHp() - damaged))
    }

    fun attack(target: EnemyView) {
        action = ACTION_ATTACK
        state.update()

        val damage_dealt = max(1, model.getPlayerAttack().toInt() - target.model.getEnemyDef().toFloat().toInt())
        target.isDamaged(damage_dealt)
    }

    fun setItems(items: Array<com.hereams.neverland.gameObjects.view.component.item.Item>) {
        inventory.addItems(items)
    }

    fun setItem(weapon: com.hereams.neverland.gameObjects.view.component.item.Weapon) {
        inventory.addItem(weapon)
    }

//    fun setItem(potion: ) {
//        inventory.addItem(potion)
//    }

    fun gainEXPFromEnemy(exp_amount: Int) {
        model.setPlayerExp(exp_amount)
    }

    fun gainGoldFromEnemy(gold_amount: Int) {
        inventory.setInventoryGold(gold_amount)
    }

}