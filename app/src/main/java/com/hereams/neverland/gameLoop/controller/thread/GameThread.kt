package com.hereams.neverland.gameLoop.controller.thread

import android.graphics.Canvas
import com.hereams.neverland.gameObjects.view.CharacterView
import com.hereams.neverland.gameObjects.view.DPadView

class GameThread(private var view: CharacterView) : Thread() {

    private var running: Boolean = false
    private lateinit var d_pad: DPadView

    fun setStart(value: Boolean) {
        running = value
    }

    fun addDPad(value: DPadView) {
        d_pad = value
    }

    override fun run() {
        while(running) {
            var v: Canvas? = null
            try {
                v = view.holder.lockCanvas()
                synchronized(view.holder) {
                    view.onDraw(v)
                }
            } finally {
                if(v != null)
                    view.holder.unlockCanvasAndPost(v)
            }
        }

    }

}