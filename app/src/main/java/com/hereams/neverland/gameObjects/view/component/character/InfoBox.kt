package com.hereams.neverland.gameObjects.view.component.character

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.hereams.neverland.gameObjects.view.component.character.CharacterView

class InfoBox(context: Context, val owner: CharacterView) {

    private val border_paint: Paint = Paint()
    private val hp_paint: Paint = Paint()
    private val exp_paint: Paint = Paint()
    private val background_paint: Paint = Paint()

    private val background_id = 0
    private val hp_id = 1
    private val exp_id = 2

    private val width: Array<Float> = arrayOf(300f, 250f)
    private val height: Array<Float> = arrayOf(150f, 30f)
    private val margin_top = 15f
    private val margin_left = 25f
    private var hp_scale: Float = 1f

    init {
        border_paint.color = Color.YELLOW
        hp_paint.color = Color.RED
        exp_paint.color = Color.GREEN
        background_paint.color = Color.WHITE
    }

    fun draw(canvas: Canvas?) {
        canvas?.drawRect(0f, 0f, width[background_id], height[background_id], background_paint)
        canvas?.drawRect(
            margin_left,
            margin_top,
            (width[hp_id] * hp_scale + margin_left),
            height[hp_id] + margin_top,
            hp_paint
        )
        canvas?.drawRect(
            margin_left,
            margin_top * 2 + height[hp_id],
            width[hp_id] + margin_left,
            margin_top * 2 + height[hp_id] * 2,
            exp_paint
        )
    }

    fun update() {
        hp_scale = owner.model.getPlayerHp().toFloat() / owner.getMaxCharacterHP().toFloat()
    }

}