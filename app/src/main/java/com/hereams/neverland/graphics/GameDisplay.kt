package com.hereams.neverland.graphics

import android.graphics.PointF
import android.graphics.Rect
import com.hereams.neverland.gameObjects.GameObject

class GameDisplay(
    private val widthPixels: Int,
    private val heightPixels: Int,
    private val centerObject: GameObject?
) {
    var DISPLAY_RECT: Rect
    private var displayCenter: PointF
    private lateinit var gameToDisplayCoordinatesOffset: PointF
    private lateinit var gameCenter: PointF

    init {
        DISPLAY_RECT = Rect(0, 0,widthPixels, heightPixels)
        displayCenter = PointF(widthPixels / 2f, heightPixels / 2f)
        update()
    }

    fun update() {
        gameCenter = centerObject?.getObjectPosition()!!
        gameToDisplayCoordinatesOffset =
            PointF(displayCenter.x - gameCenter.x, displayCenter.y - gameCenter.y)
    }

    fun gameToDisplayCoordinatesX(x: Float): Float {
        return x + gameToDisplayCoordinatesOffset.x
    }

    fun gameToDisplayCoordinatesY(y: Float): Float {
        return y + gameToDisplayCoordinatesOffset.y
    }

    fun getGameRect(): Rect? {
        return Rect(
            (gameCenter.x - widthPixels / 2).toInt(),
            (gameCenter.y - heightPixels / 2).toInt(),
            (gameCenter.x + widthPixels / 2).toInt(),
            (gameCenter.y + heightPixels / 2).toInt()
        )
    }
}