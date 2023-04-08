package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import com.hereams.neverland.gameObjects.GameObject
import com.hereams.neverland.graphics.GameDisplay


abstract class Circle(
    context: Context?,
    color: Int, position: PointF,
    var radius: Float
) : GameObject(position) {

    private var paint: Paint = Paint()

    init {

        // Set colors of circle
        paint.color = color
    }

    override fun draw(canvas: Canvas?, gameDisplay: GameDisplay) {
        canvas?.drawCircle(
            gameDisplay.gameToDisplayCoordinatesX(position.x),
            gameDisplay.gameToDisplayCoordinatesY(position.y), radius,
            paint
        )
    }

    companion object {
        /**
         * isColliding checks if two circle objects are colliding, based on their positions and radius.
         * @param obj1
         * @param obj2
         * @return
         */
        fun isColliding(obj1: Circle, obj2: Circle): Boolean {
            val distance: Float = getDistanceBetweenObjects(obj1, obj2)
            val distanceToCollision = obj1.radius + obj2.radius
            return distance < distanceToCollision
        }
    }
}