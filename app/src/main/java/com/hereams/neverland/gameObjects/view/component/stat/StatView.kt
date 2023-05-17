package com.hereams.neverland.gameObjects.view.component.stat

import android.graphics.*
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.R
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
    private lateinit var increase_btn_paint: Paint

    private lateinit var container_rect: Rect
    private lateinit var character_rect: Rect
    private lateinit var stat_rect: Rect
    private lateinit var increase_stat_rect: Rect
    private lateinit var reset_btn_rect: Rect
    private lateinit var close_rect: Rect
    private lateinit var increase_btn_rect_array: MutableList<Rect>

    private lateinit var model: com.hereams.neverland.gameObjects.model.Character
    private lateinit var sf_holder: SurfaceHolder
    private lateinit var stat_loop: StatLoop
    private lateinit var equipment: MutableList<Weapon>
    private lateinit var owner: CharacterView
    private lateinit var bitmap: Bitmap

    init {
        setBackgroundColor(Color.TRANSPARENT)

        initPaints()

        initRects()

        val bitmapOptions = BitmapFactory.Options().apply {
            inScaled = false
        }

        val zoomMatrix: Matrix = Matrix().apply {
            setScale(8f, 8f)
        }
        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.btn_stat, bitmapOptions)
        bitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.width, bitmap.height, zoomMatrix, true)

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

        increase_btn_paint = Paint().apply {
            color = Color.RED
            style = Paint.Style.FILL_AND_STROKE
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

        increase_btn_rect_array = mutableListOf()
        for (i in 0 until 5) {
            increase_btn_rect_array.add(
                i, Rect(
                    increase_stat_rect.left + 240,
                    increase_stat_rect.top + 70 * i + 100,
                    increase_stat_rect.left + 240 + 40,
                    increase_stat_rect.top + 70 * i + 40 + 100
                )
            )
        }

        reset_btn_rect = Rect(
            (increase_stat_rect.left),
            (increase_stat_rect.bottom + 50),
            (increase_stat_rect.right),
            (increase_stat_rect.bottom + 50 + 75)
        )
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
        if (stat_loop.state.equals(Thread.State.TERMINATED)) {
            sf_holder = getHolder()
            sf_holder.addCallback(this)
            stat_loop = StatLoop(this, sf_holder)
        }
        stat_loop.startLoop()
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

        drawCharacterField(canvas)
        drawStatField(canvas)
        drawIncreaseStatField(canvas)
        drawButtonField(canvas)
    }

    fun drawCharacterField(canvas: Canvas?) {
        canvas?.drawRect(
            character_rect,
            character_paint
        )

        canvas?.drawBitmap(bitmap, null, character_rect, null)
    }

    fun drawStatField(canvas: Canvas?) {
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
            "HP: ${model.getPlayerMaxHp().toInt()}",
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
    }

    private val margin_bottom = 70
    fun drawIncreaseStatField(canvas: Canvas?) {
        //draw increase field
        canvas?.drawRect(
            increase_stat_rect,
            increase_stat_paint
        )

        canvas?.drawText(
            "Stat Point(s): ${model.getStatPoint()}",
            (increase_stat_rect.left + 60).toFloat(),
            (increase_stat_rect.top + 60).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "VIT:  ${model.getPlayerMaxHp()}",
            (increase_stat_rect.left + 60).toFloat(),
            (increase_stat_rect.top + 60 + margin_bottom).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "INT:  ${model.getINT()}",
            (increase_stat_rect.left + 60).toFloat(),
            (increase_stat_rect.top + 60 + margin_bottom * 2).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "STR: ${model.getSTR()}",
            (increase_stat_rect.left + 60).toFloat(),
            (increase_stat_rect.top + 60 + margin_bottom * 3).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "DEX: ${model.getDEX()}",
            (increase_stat_rect.left + 60).toFloat(),
            (increase_stat_rect.top + 60 + margin_bottom * 4).toFloat(),
            text_paint
        )

        canvas?.drawText(
            "CRT: ${model.getCRT()}",
            (increase_stat_rect.left + 60).toFloat(),
            (increase_stat_rect.top + 60 + margin_bottom * 5).toFloat(),
            text_paint
        )

        for (i in 0 until 5) {
            canvas?.drawRect(increase_btn_rect_array[i], increase_btn_paint)
        }

    }

    fun drawButtonField(canvas: Canvas?) {
        //draw close button
        canvas?.drawRect(
            close_rect,
            close_paint
        )

        canvas?.drawRect(
            reset_btn_rect,
            reset_btn_paint
        )

        canvas?.drawText("Reset",
            reset_btn_rect.left.toFloat() + 70,
            reset_btn_rect.bottom.toFloat() - 15,
            Paint().apply
            {
                textSize = 60F
                color = Color.BLACK
            })
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val x = event!!.x.toInt()
        val y = event.y.toInt()

        when (event!!.actionMasked) {
            MotionEvent.ACTION_DOWN -> {

                if (close_rect.contains(x, y)) {
                    stat_loop.stopLoop()
                    layout.removeView(this)
                }

                // increase VIT
                else if (increase_btn_rect_array[0].contains(x, y)) {
                    if (isEnoughStatPoint()) {
                        model.setVIT(model.getVIT() + 1)
                    }
                }

                // increase INT
                else if (increase_btn_rect_array[1].contains(x, y)) {
                    if (isEnoughStatPoint()) {
                        model.setINT(model.getINT() + 1)
                    }
                }

                // increase STR
                else if (increase_btn_rect_array[2].contains(x, y)) {
                    if (isEnoughStatPoint()) {
                        model.setSTR(model.getSTR() + 1)
                    }
                }

                // increase DEX
                else if (increase_btn_rect_array[3].contains(x, y)) {
                    if (isEnoughStatPoint()) {
                        model.setDEX(model.getDEX() + 1)
                    }
                }

                // increase CRT
                else if (increase_btn_rect_array[4].contains(x, y)) {
                    if (isEnoughStatPoint()) {
                        model.setCRT(model.getCRT() + 1)
                    }
                }

                return true
            }
            MotionEvent.ACTION_UP -> {

            }
        }

        return super.onTouchEvent(event)
    }

    private fun isEnoughStatPoint(): Boolean {
        if (model.getStatPoint() >= 1) {
            model.setStatPoint(model.getStatPoint() - 1)
            return true
        }

        return false

    }

    fun setOwner(character: CharacterView) {
        owner = character
        model = owner.model
    }

}