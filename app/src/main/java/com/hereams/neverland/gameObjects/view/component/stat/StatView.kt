package com.hereams.neverland.gameObjects.view.component.stat

import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.gameLoop.thread.StatLoop
import com.hereams.neverland.gameObjects.model.Inventory
import com.hereams.neverland.gameObjects.view.component.character.CharacterView
import com.hereams.neverland.gameObjects.view.component.item.Weapon
import com.hereams.neverland.gameObjects.view.layout.GameLayout

class StatView(
    val layout: GameLayout,
    val screen_size: PointF,
) : SurfaceView(layout.context),
    SurfaceHolder.Callback {

    private lateinit var container_paint: Paint
    private lateinit var character_paint: Paint
    private lateinit var stat_paint: Paint
    private lateinit var increase_stat_paint: Paint
    private lateinit var apply_btn_paint: Paint
    private lateinit var reset_btn_paint: Paint
    private lateinit var text_paint: Paint
    private lateinit var close_paint: Paint

    private lateinit var container_rect: Rect
    private lateinit var character_rect: Rect
    private lateinit var stat_rect: Rect
    private lateinit var increase_stat_rect: Rect
    private lateinit var apply_btn_rect: Rect
    private lateinit var reset_btn_rect: Rect
    private lateinit var close_rect: Rect

    private lateinit var model: com.hereams.neverland.gameObjects.model.Character
    private lateinit var sf_holder: SurfaceHolder
    private lateinit var stat_loop: StatLoop
    private lateinit var equipment: MutableList<Weapon>
    private lateinit var owner: CharacterView

    init {
        setBackgroundColor(Color.TRANSPARENT)

        initPaints()

        initRects()

        sf_holder = holder
        sf_holder.addCallback(this)
        stat_loop = StatLoop(this, sf_holder)
    }

    private fun initPaints() {
        container_paint = Paint().apply {
            color = Color.YELLOW
            style = Paint.Style.FILL_AND_STROKE
        }

        character_paint = Paint().apply {
            color = Color.WHITE
            style = Paint.Style.FILL_AND_STROKE
        }

        stat_paint = character_paint
        increase_stat_paint = character_paint

        apply_btn_paint = Paint().apply {
            color = Color.MAGENTA
            style = Paint.Style.FILL_AND_STROKE
        }

        reset_btn_paint = apply_btn_paint

        text_paint = Paint().apply {
            color = Color.BLACK
            textSize = 30f
        }
        close_paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL
        }
    }

    private fun initRects() {
        container_rect = Rect(
            (screen_size.x * 0.3).toInt(),
            (screen_size.y * 0.1).toInt(),
            (screen_size.x * 0.7).toInt(),
            (screen_size.y * 0.9).toInt()
        )

        character_rect = Rect(
            (container_rect.left + 100),
            (container_rect.top + 100),
            (container_rect.left + container_rect.width() * 0.5).toInt(),
            (container_rect.top + container_rect.height() * 0.65).toInt()
        )

        stat_rect = Rect(
            (container_rect.left + 100),
            (container_rect.top + 50 + 100 + character_rect.height()),
            (container_rect.left + container_rect.width() * 0.5).toInt(),
            (container_rect.top + container_rect.height() * 0.95).toInt()
        )

        increase_stat_rect = Rect(
            (container_rect.left + 100 + character_rect.width() + 50),
            (container_rect.top + 100),
            (container_rect.left + 100 + character_rect.width() + 50 + container_rect.width() * 0.35).toInt(),
            (container_rect.top + container_rect.height() * 0.65).toInt()
        )

        close_rect = Rect(
            container_rect.right - 40,
            container_rect.top,
            container_rect.right,
            container_rect.top + 40
        )
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        sf_holder = getHolder()
        sf_holder.addCallback(this)
        stat_loop = StatLoop(this, sf_holder)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
//        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
//        TODO("Not yet implemented")
    }

    fun update() {

    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
//
        //draw stat container
        canvas?.drawRect(
            container_rect,
            container_paint
        )

        //draw character field
        canvas?.drawRect(
            character_rect,
            character_paint
        )

        //draw stat field
        canvas?.drawRect(
            stat_rect,
            stat_paint
        )

        canvas?.drawText(
            "ATK: ${model.getPlayerAttack()}",
            (stat_rect.left + 60).toFloat(),
            (stat_rect.top + 60).toFloat(),
            text_paint
            )

        canvas?.drawText(
            "HP: ${model.getPlayerHp().toInt()}",
            (stat_rect.left + 60).toFloat(),
            (stat_rect.top + 60 + 30).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "DEF: ${model.getPlayerDef()}",
            (stat_rect.left + 60).toFloat(),
            (stat_rect.top + 60 + 60).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "ATK Speed: ${model.getAttackSpeed()}",
            (stat_rect.left + 60).toFloat(),
            (stat_rect.top + 60 + 90).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "Movement: ${model.getMoveSpeed()}",
            (stat_rect.left + 60).toFloat(),
            (stat_rect.top + 60 + 120).toFloat(),
            text_paint
        )

        //draw increase field
        canvas?.drawRect(
            increase_stat_rect,
            increase_stat_paint
        )
//
        //draw close button
        canvas?.drawRect(
            close_rect,
            close_paint
        )
//
//        var item_x = inventory_rect.left
//        var item_y = inventory_rect.top
//        var index = 0
//
//        //draw inventory items
//        while (index < items.size) {
//
//            for (i in 0 until min(items.size - index, INVENTORY_MAX_COLUMN)) {
//                items[index].draw(Rect(item_x, item_y, item_x + 64, item_y + 64), canvas)
//                ++index
//                item_x += 65
//            }
//
//            item_x = inventory_rect.left
//
//            item_y += 65
//
//        }
//
////        draw inventory info
//        canvas?.drawRect(
//            info_rect,
//            info_paint
//        )
//        canvas?.drawText(
//            "Gold: ${model.getInventoryGold()}",
//            (screen_size.x * 0.52).toFloat(), (screen_size.y * 0.73).toFloat(), info_text_paint
//        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x.toInt()
        val y = event.y.toInt()

        if (close_rect.contains(x, y)) {
            layout.removeView(this)
        }

        return super.onTouchEvent(event)
    }

    fun setOwner(character: CharacterView) {
        owner = character
        model = owner.model
    }

}