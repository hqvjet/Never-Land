package com.hereams.neverland.graphics

import android.graphics.Rect
import com.hereams.neverland.gameObjects.GameObject

class GameDisplay(
    private val widthPixels: Int,
    private val heightPixels: Int,
    private val centerObject: GameObject?
) {
    var DISPLAY_RECT: Rect? = null
    private var displayCenterX = 0f
    private var displayCenterY = 0f
    private var gameToDisplayCoordinatesOffsetX = 0f
    private var gameToDisplayCoordinatesOffsetY = 0f
    private var gameCenterX = 0f
    private var gameCenterY = 0f

    init {
        DISPLAY_RECT = Rect(0, 0, widthPixels, heightPixels)
        displayCenterX = widthPixels / 2f
        displayCenterY = heightPixels / 2f
        update()
    }

    fun update() {
        gameCenterX = centerObject!!.getObjectPosition().x
        gameCenterY = centerObject.getObjectPosition().y
        gameToDisplayCoordinatesOffsetX = displayCenterX - gameCenterX
        gameToDisplayCoordinatesOffsetY = displayCenterY - gameCenterY
    }

    fun gameToDisplayCoordinatesX(x: Float): Float {
        return x + gameToDisplayCoordinatesOffsetX
    }

    fun gameToDisplayCoordinatesY(y: Float): Float {
        return y + gameToDisplayCoordinatesOffsetY
    }

    fun getGameRect(): Rect? {
        return Rect(
            (gameCenterX - widthPixels / 2).toInt(),
            (gameCenterY - heightPixels / 2).toInt(),
            (gameCenterX + widthPixels / 2).toInt(),
            (gameCenterY + heightPixels / 2).toInt()
        )
    }
}