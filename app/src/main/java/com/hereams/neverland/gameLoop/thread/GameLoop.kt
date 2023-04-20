package com.hereams.neverland.gameLoop.thread

import android.graphics.Canvas
import android.view.SurfaceHolder
import com.hereams.neverland.constant.MAX_UPS
import com.hereams.neverland.constant.UPS_PERIOD
import com.hereams.neverland.gameObjects.Game

/**
 * This class is used for handling game update
 *
 * Extends: Thread
 */

class GameLoop(private val game: Game, private val surfaceHolder: SurfaceHolder) : Thread() {

    private var is_running: Boolean = false
    private var averageUPS: Double = 0.0
    private var averageFPS: Double = 0.0

    fun startLoop() {
        is_running = true
        start()
    }

    override fun run() {
        super.run()

        var canvas: Canvas? = null
        var updateCount: Int = 0
        var frameCount: Int = 0
        var elapsedTime: Long = 0
        var sleepTime: Long = 0
        var startTime: Long = System.currentTimeMillis()

        while (is_running) {

            //Try to update and render game
            try {
                game.postInvalidate()
                canvas = surfaceHolder.lockCanvas()
                synchronized(surfaceHolder) {
                    game.update(averageFPS.toFloat())
                    ++updateCount
                    game.draw(canvas)
                }
            } catch (e: IllegalArgumentException) {
                e.printStackTrace()
            } finally {
                if (canvas != null) {
                    try {
                        surfaceHolder.unlockCanvasAndPost(canvas)
                        frameCount++
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }

                }
            }

            // Pause game loop to not exceed target UPS
            elapsedTime = System.currentTimeMillis() - startTime
            sleepTime = (updateCount * UPS_PERIOD - elapsedTime).toLong()
            if (sleepTime > 0) {
                try {
                    sleep(sleepTime)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }

            // Skip frames to keep up with target UPS
            while (sleepTime < 0 && updateCount < MAX_UPS - 1) {
                game.update(averageFPS.toFloat())
                updateCount++
                elapsedTime = System.currentTimeMillis() - startTime
                sleepTime = (updateCount * UPS_PERIOD - elapsedTime).toLong()
            }

            // Calculate average UPS and FPS
            elapsedTime = System.currentTimeMillis() - startTime
            if (elapsedTime >= 1000) {
                averageUPS = updateCount / (1E-3 * elapsedTime)
                averageFPS = frameCount / (1E-3 * elapsedTime)
                updateCount = 0
                frameCount = 0
                startTime = System.currentTimeMillis()
            }
        }


    }

    fun stopLoop() {
        is_running = false
        try {
            join()
        } catch (e: InterruptedException) {
            e.printStackTrace()
        }
    }

}