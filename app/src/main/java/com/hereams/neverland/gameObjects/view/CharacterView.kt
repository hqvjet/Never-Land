package com.hereams.neverland.gameObjects.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Matrix
import android.graphics.PointF
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.R
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameLoop.controller.thread.CharacterMotionThread
import com.hereams.neverland.gameLoop.controller.thread.CharacterThread
import com.hereams.neverland.gameLoop.service.CharacterService
import com.hereams.neverland.gameObjects.model.Character
import com.hereams.neverland.gameObjects.model.Inventory
import com.hereams.neverland.gameObjects.model.Option
import com.hereams.neverland.gameObjects.model.Weapon

class CharacterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {
    var service: CharacterService = CharacterService(this)
    private val helper: Helper = Helper(this)
//    var thread: CharacterThread = CharacterThread(service)
    var move_thread: CharacterMotionThread = CharacterMotionThread(this)

    var sf_holder: SurfaceHolder = holder
    lateinit var canvas: Canvas

    //    private lateinit var spriteWidth: Number
//    private lateinit var spriteHeight: Number
    lateinit var model: Character

    private var originBitmap: Bitmap
    private var bitmap: Bitmap
    var position: PointF = PointF(helper.toDP(100f), helper.toDP(100f))
    var velocity: PointF = PointF(helper.toDP(0f), helper.toDP(0f))

    init {

        //convert static bitmap to mutable bitmap
        setBackgroundColor(Color.TRANSPARENT)
        originBitmap = BitmapFactory.decodeResource(resources, R.mipmap.character)
        val matrix = Matrix()
        matrix.setScale(300.toFloat() / originBitmap.width, 300.toFloat() / originBitmap.height)
        bitmap = Bitmap.createBitmap(
            originBitmap,
            0,
            0,
            originBitmap.width,
            originBitmap.height,
            matrix,
            false
        )

        model = Character(
            "Jack", 1, 1, CLASS_NAME[KNIGHT], 1, 1, BASE_ATTACK[KNIGHT],
            BASE_DEF[KNIGHT], BASE_HP[KNIGHT], 1, 1, 1, 1, 1, BASE_MOVE[KNIGHT],
            Weapon(
                ITEM_NAME[STEEL_SWORD],
                ITEM_DESCRIPTION[STEEL_SWORD],
                ITEM_PRICE[STEEL_SWORD],
                ITEM_CLASSIFY[STEEL_SWORD],
                ITEM_DISCARDABLE[STEEL_SWORD],
                ITEM_TRADEALBE[STEEL_SWORD],
                ITEM_STAT[STEEL_SWORD],
                1
            ),
            Inventory(10, 1000),
            Option(EN, "jacky", "1231233")
        )

        sf_holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
//                thread.turnOn()
                move_thread.turnOn()
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                var retry: Boolean = true
                while (retry) {
                    try {
//                        thread.turnOff()
                        move_thread.turnOff()
                        retry = false
                    } catch (e: InterruptedException) {
                    }
                }
            }
        })
    }

    public override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(Color.TRANSPARENT)
//        println("${position.x} ${position.y}")
        canvas?.drawBitmap(bitmap, position.x, position.y, null)
    }

}