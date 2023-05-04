package com.hereams.neverland.gameObjects

import android.graphics.Canvas
import android.graphics.PointF
import com.hereams.neverland.constant.ACTION_MOVE
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState
import com.hereams.neverland.gameObjects.view.component.map.Tile
import com.hereams.neverland.graphics.GameDisplay
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * GameObject is an abstract class which is the foundation of all world objects in the game.
 */
abstract class GameObject {
    protected var position: PointF
    protected var velocity: PointF = PointF(0f, 0f)
    protected var direction: LivingAnimationObjectState.State =
        LivingAnimationObjectState.State.IS_MOVING_FORWARD
    protected var action: String = ACTION_MOVE
    protected var ready_to_attack: Boolean = true
    protected var is_attacking: Boolean = false

    constructor(position: PointF) {
        this.position = position
    }

    fun getObjectPosition(): PointF {
        return position
    }

    fun getObjectVelocity(): PointF {
        return velocity
    }

    @JvmName("getAction1")
    fun getAction(): String {
        return action
    }

    @JvmName("setAction1")
    fun setAction(value: String) {
        action = value
    }

    @JvmName("getDirection1")
    fun getDirection(): LivingAnimationObjectState.State {
        return direction
    }

    @JvmName("setDirection1")
    fun setDirection(value: LivingAnimationObjectState.State) {
        direction = value
    }

    fun readyToAttack(): Boolean {
        return ready_to_attack
    }

    fun setReadyToAttack(value: Boolean) {
        ready_to_attack = value
    }

    fun isAttacking(): Boolean {
        return is_attacking
    }

    fun setIsAttacking(value: Boolean) {
        is_attacking = value
    }

    abstract fun draw(canvas: Canvas?, gameDisplay: GameDisplay)
    abstract fun update(fps: Float, obstacle_list: MutableList<Tile>)
    abstract fun getState(): LivingAnimationObjectState.State

    companion object {
        /**
         * getDistanceBetweenObjects returns the distance between two game objects
         * @param obj1
         * @param obj2
         * @return
         */
        fun getDistanceBetweenObjects(obj1: GameObject, obj2: GameObject): Float {
            return sqrt(
                (obj2.position.x - obj1.position.x).pow(2) +
                        (obj2.position.y - obj1.position.y).pow(2)
            )
        }
    }
}