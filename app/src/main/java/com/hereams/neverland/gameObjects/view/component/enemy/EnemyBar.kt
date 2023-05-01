package com.hereams.neverland.gameObjects.view.component.enemy

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import com.hereams.neverland.constant.SPRITES_SIZE
import com.hereams.neverland.gameObjects.view.component.enemy.EnemyView
import com.hereams.neverland.graphics.GameDisplay

class EnemyBar(context: Context, val owner: EnemyView) {

    private val hp_paint: Paint = Paint()

    private val width: Float = 100f
    private val height: Float = 30f
    private val margin_bottom: Float = 80f + SPRITES_SIZE / 8
    private var hp_scale: Float = 1f

    init {
        hp_paint.color = Color.RED
    }

    fun draw(canvas: Canvas?, game_display: GameDisplay) {
        canvas?.drawRect(
            game_display.gameToDisplayCoordinatesX(owner.getObjectPosition().x - width / 2),
            game_display.gameToDisplayCoordinatesY(owner.getObjectPosition().y - margin_bottom),
            game_display.gameToDisplayCoordinatesX(owner.getObjectPosition().x + (width * hp_scale) - width / 2),
            game_display.gameToDisplayCoordinatesY(owner.getObjectPosition().y - margin_bottom + 20f),
            hp_paint
        )
    }

    fun update() {
        hp_scale = owner.model.getEnemyHp().toFloat() / owner.MAX_HP.toFloat()
    }

}