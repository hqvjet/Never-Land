package com.hereams.neverland.gameObjects.view.component.inventory

import android.content.Context
import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.constant.INVENTORY_MAX_COLUMN
import com.hereams.neverland.gameLoop.thread.InventoryLoop
import com.hereams.neverland.gameObjects.model.Inventory
import com.hereams.neverland.gameObjects.view.component.character.CharacterView
import com.hereams.neverland.gameObjects.view.component.item.Item
import com.hereams.neverland.gameObjects.view.layout.GameLayout
import kotlin.math.min

class InventoryView(
    val layout: GameLayout,
    val screen_size: PointF,
) : SurfaceView(layout.context),
    SurfaceHolder.Callback {

    private lateinit var inventory_paint: Paint
    private lateinit var info_paint: Paint
    private lateinit var info_text_paint: Paint
    private lateinit var close_paint: Paint

    private lateinit var model: Inventory
    private lateinit var sf_holder: SurfaceHolder
    private lateinit var inventory_loop: InventoryLoop
    private lateinit var items: MutableList<Item>
    private lateinit var owner: CharacterView
    private lateinit var inventory_rect: Rect
    private lateinit var info_rect: Rect
    private lateinit var close_rect: Rect

    init {
        setBackgroundColor(Color.TRANSPARENT)
        inventory_paint = Paint()
        inventory_paint.color = Color.YELLOW
        info_paint = Paint()
        info_paint.color = Color.RED
        info_text_paint = Paint().apply {
            color = Color.BLACK
            textSize = 30f
        }
        close_paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL
        }

        inventory_rect = Rect(
            (screen_size.x * 0.2).toInt(),
            (screen_size.y * 0.2).toInt(),
            (screen_size.x * 0.8).toInt(),
            (screen_size.y * 0.8).toInt()
        )
        info_rect = Rect(
            (screen_size.x * 0.5).toInt(),
            (screen_size.y * 0.7).toInt(),
            (screen_size.x * 0.8).toInt(),
            (screen_size.y * 0.755).toInt()
        )
        close_rect = Rect(
            inventory_rect.right - 40,
            inventory_rect.top,
            inventory_rect.right,
            inventory_rect.top + 40
        )

        sf_holder = holder
        sf_holder.addCallback(this)
        inventory_loop = InventoryLoop(this, sf_holder)
        model = Inventory(10, 1000)
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        sf_holder = getHolder()
        sf_holder.addCallback(this)
        inventory_loop = InventoryLoop(this, sf_holder)
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
//        TODO("Not yet implemented")
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
//        TODO("Not yet implemented")
    }

    fun update() {
        items = owner.inventory.getItems()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)

        //draw inventory container
        canvas?.drawRect(
            inventory_rect,
            inventory_paint
        )

        //draw close button
        canvas?.drawRect(
            close_rect,
            close_paint
        )

        var item_x = inventory_rect.left
        var item_y = inventory_rect.top
        var index = 0

        //draw inventory items
        while (index < items.size) {

            for (i in 0 until min(items.size - index, INVENTORY_MAX_COLUMN)) {
                items[index].draw(Rect(item_x, item_y, item_x + 64, item_y + 64), canvas)
                ++index
                item_x += 65
            }

            item_x = inventory_rect.left

            item_y += 65

        }

//        draw inventory info
        canvas?.drawRect(
            info_rect,
            info_paint
        )
        canvas?.drawText(
            "Gold: ${model.getInventoryGold()}",
            (screen_size.x * 0.52).toFloat(), (screen_size.y * 0.73).toFloat(), info_text_paint
        )
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x.toInt()
        val y = event.y.toInt()

        if (close_rect.contains(x, y)) {
            layout.removeView(this)
        }

        return super.onTouchEvent(event)
    }

    fun setModel(model: Inventory) {
        this.model = model
    }

    fun getModel(): Inventory {
        return model
    }

    fun setOwner(character: CharacterView) {
        owner = character
        items = owner.inventory.getItems()
    }

}