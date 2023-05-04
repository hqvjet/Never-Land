package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Paint
import android.graphics.PointF
import android.graphics.Rect
import com.hereams.neverland.constant.CIRCLE_RADIUS
import com.hereams.neverland.gameObjects.GameObject
import com.hereams.neverland.gameObjects.view.component.map.Tile
import com.hereams.neverland.graphics.GameDisplay
import com.hereams.neverland.tool.FloatRect
import kotlin.math.abs


abstract class Circle(
    context: Context?,
    color: Int, position: PointF,
    var radius: Float
) : GameObject(position) {

    private var paint: Paint = Paint()
    private var x_zone = false
    private var y_zone = false

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

    fun handlingIsBlockedByObstacle(obstacle_list: MutableList<Tile>) {


        position.x += velocity.x
        position.y += velocity.y

        val character_rect = FloatRect(
            position.x - CIRCLE_RADIUS,
            position.y - CIRCLE_RADIUS,
            position.x + CIRCLE_RADIUS,
            position.y + CIRCLE_RADIUS
        )

        for(i in 0 until obstacle_list.size) {

            val obstacle_rect: Rect = obstacle_list[i].getRect()
            if(character_rect.intersects(obstacle_rect)) {
                // blocked vertical
                if(character_rect.right - velocity.x <= obstacle_rect.left || character_rect.left - velocity.x >= obstacle_rect.right) {
                    position.x -= velocity.x
                }

                // blocked horizontal
                if(character_rect.bottom - velocity.y <= obstacle_rect.top || character_rect.top - velocity.y >= obstacle_rect.bottom) {
                    position.y -= velocity.y
                }
            }
        }
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