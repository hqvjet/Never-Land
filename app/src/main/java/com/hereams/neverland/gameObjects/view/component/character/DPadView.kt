package com.hereams.neverland.gameObjects.view.component.character

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.PointF
import kotlin.math.pow
import kotlin.math.sqrt

class DPadView(
    val center_position: PointF,
    val inner_radius: Float,
    val outer_radius: Float
) {

    private val outer_paint = Paint().apply {
        color = Color.GRAY
        style = Paint.Style.FILL_AND_STROKE
    }

    private val inner_paint = Paint().apply {
        color = Color.RED
        style = Paint.Style.FILL_AND_STROKE
    }

    //    var service: DPadService = DPadService(this, character_view)
    lateinit var canvas: Canvas
    private var is_pressed: Boolean = false

    //Positions
    var actuator = PointF(0f, 0f)
    var inner_circle_position: PointF = PointF(center_position.x, center_position.y)
    var outer_circle_position: PointF = PointF(center_position.x, center_position.y)

//    var thread: DPadThread

    init {

    }

    fun draw(canvas: Canvas?) {

        canvas?.drawCircle(
            outer_circle_position.x,
            outer_circle_position.y,
            outer_radius,
            outer_paint
        )

        canvas?.drawCircle(
            inner_circle_position.x,
            inner_circle_position.y,
            inner_radius,
            inner_paint
        )
    }

    fun update() {
        updateInnerCirclePosition()
    }

    private fun updateInnerCirclePosition() {
        inner_circle_position.x = outer_circle_position.x + actuator.x * outer_radius
        inner_circle_position.y = outer_circle_position.y + actuator.y * outer_radius
    }

    fun setDpadActuator(touched_position: PointF) {
        val delta = PointF(
            touched_position.x - outer_circle_position.x,
            touched_position.y - outer_circle_position.y
        )
        val deltaDistance = sqrt(delta.x * delta.x + delta.y * delta.y)
        if (deltaDistance < outer_radius) {
            actuator.x = delta.x / outer_radius
            actuator.y = delta.y / outer_radius
        } else {
            actuator.x = delta.x / deltaDistance
            actuator.y = delta.y / deltaDistance
        }
//        println("${actuator.x} ${actuator.y}")
    }

    fun isPressed(touched_position: PointF): Boolean {
        val DPAD_center_to_touch_distance = sqrt(
            (outer_circle_position.x - touched_position.x).pow(2) +
                    (outer_circle_position.y - touched_position.y).pow(2)
        )
        return DPAD_center_to_touch_distance < outer_radius
    }

    fun getIsPressed(): Boolean {
        return is_pressed
    }

    fun setIsPressed(isPressed: Boolean) {
        is_pressed = isPressed
    }

    fun getActuatorX(): Float {
        return actuator.x
    }

    fun getActuatorY(): Float {
        return actuator.y
    }

    fun resetActuator() {
        actuator = PointF(0f, 0f)
    }

}