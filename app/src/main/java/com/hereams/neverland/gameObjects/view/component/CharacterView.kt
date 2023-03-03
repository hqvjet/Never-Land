package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.PointF
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.constant.*
import com.hereams.neverland.gameLoop.controller.thread.CharacterMotionThread
import com.hereams.neverland.gameLoop.service.CharacterService
import com.hereams.neverland.gameObjects.model.Character
import com.hereams.neverland.gameObjects.model.Inventory
import com.hereams.neverland.gameObjects.model.Option
import com.hereams.neverland.gameObjects.model.Weapon
import com.hereams.neverland.gameObjects.states.CharacterState
import com.hereams.neverland.graphics.Animator
import com.hereams.neverland.graphics.SpritesSheet

class CharacterView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {
    var service: CharacterService = CharacterService(this)
    private val helper: Helper = Helper(this)
    var move_thread: CharacterMotionThread = CharacterMotionThread(this)

    var sf_holder: SurfaceHolder = holder
    lateinit var canvas: Canvas
    private lateinit var spritesSheet: SpritesSheet
    lateinit var animator: Animator
    lateinit var state: CharacterState

    //    private lateinit var spriteWidth: Number
//    private lateinit var spriteHeight: Number
    lateinit var model: Character

    //    private var originBitmap: Bitmap
//    private var bitmap: Bitmap
    var position: PointF = PointF(
        helper.toDP((helper.getDeviceWidth() / 2).toFloat()),
        helper.toDP((helper.getDeviceHeight() / 2.toFloat()))
    )
    var velocity: PointF = PointF(helper.toDP(0f), helper.toDP(0f))

    init {

        setBackgroundColor(Color.TRANSPARENT)
        spritesSheet = SpritesSheet(position, context)
        animator = Animator(position, spritesSheet.getCharacterSpritesArray())
        state = CharacterState(this)
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
                        move_thread.turnOff()
                        retry = false
                    } catch (e: InterruptedException) {
                    }
                }
            }
        })
    }

    fun getState(): CharacterState.State {
        return state.getState()
    }

    public override fun onDraw(canvas: Canvas?) {
        animator.draw(canvas, this)
    }

    fun getwidth(): Float {
        return helper.toDP(width.toFloat())
    }

    fun getheight(): Float {
        return helper.toDP(height.toFloat())
    }

}