package com.hereams.neverland.gameObjects.view.component.character


import android.R
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.os.Handler
import android.view.View
import androidx.core.app.ActivityCompat.finishAffinity
import androidx.core.content.ContextCompat

/**
 * GameOver is a panel which draws the text Game Over to the screen.
 */
class GameOver(context: Context): View(context) {
    override fun onDraw(canvas: Canvas) {
        val text = "Game Over"
        val x = 750f
        val y = 250f
        val paint = Paint()
        paint.color = Color.RED
        val textSize = 150f
        paint.textSize = textSize
        canvas.drawText(text, x, y, paint)
    }
}