package com.hereams.neverland.gameObjects

import android.graphics.Canvas
import android.graphics.PointF
import com.hereams.neverland.gameObjects.states.LivingAnimationObjectState
import com.hereams.neverland.graphics.GameDisplay
import kotlin.math.pow
import kotlin.math.sqrt

/**
 * GameObject is an abstract class which is the foundation of all world objects in the game.
 */
abstract class GameObject {
    protected var position: PointF
    protected var velocity: PointF = PointF(0f, 0f)
    protected var direction: PointF = PointF(0f, 0f)

    constructor(position: PointF) {
        this.position = position
    }

    fun getObjectPosition(): PointF {
        return position
    }

    fun getObjectVelocity(): PointF {
        return velocity
    }

    abstract fun draw(canvas: Canvas?, gameDisplay: GameDisplay)
    abstract fun update(fps: Float)
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