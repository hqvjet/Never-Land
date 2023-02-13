package com.hereams.neverland.gameObjects.view

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import android.graphics.Matrix
import android.util.AttributeSet
import android.view.SurfaceHolder
import android.view.SurfaceView
import com.hereams.neverland.R
import com.hereams.neverland.gameLoop.controller.thread.GameThread
import com.hereams.neverland.gameObjects.model.Character

class CharacterView@JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : SurfaceView(context, attrs, defStyleAttr) {

    private lateinit var originBitmap: Bitmap
    private lateinit var bitmap: Bitmap
    private lateinit var sf_holder: SurfaceHolder
    private lateinit var game_thread: GameThread
    private var speedX = 0
    private var speedY = 0
    private lateinit var spriteWidth: Number
    private lateinit var spriteHeight: Number
    public lateinit var model: Character
    private lateinit var d_pad: DPadView

    init {

        //convert static bitmap to mutable bitmap
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

        sf_holder = holder
        game_thread = GameThread(this)

        sf_holder.addCallback(object : SurfaceHolder.Callback {
            override fun surfaceCreated(surfaceHolder: SurfaceHolder) {
//                var canvas: Canvas = sf_holder.lockCanvas()
//                draw(canvas)
//                sf_holder.unlockCanvasAndPost(canvas)
//                println("running")
                game_thread.setStart(true)
                game_thread.start()
            }

            override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
            }

            override fun surfaceDestroyed(p0: SurfaceHolder) {
                var retry: Boolean = true
                game_thread.setStart(false)
                while (retry) {
                    try {
                        game_thread.join()
                        retry = false
                    } catch (e: InterruptedException) {
                    }
                }
            }
        })
    }

    fun addModel(value: Character) {
        model = value
    }

    fun addDPad(value: DPadView) {
        d_pad = value
        game_thread.addDPad(d_pad)
    }

    fun move(speedx: Int, speedy: Int) {
        speedX += speedx
        speedY += speedy
    }

    public override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.drawColor(resources.getColor(R.color.teal_200))
        canvas?.drawBitmap(bitmap, speedX.toFloat(), speedY.toFloat(), null)
    }

}