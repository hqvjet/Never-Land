package com.hereams.neverland.gameObjects.view.component.item

import android.graphics.Bitmap
import android.graphics.Canvas
import android.graphics.Rect

abstract class Item {

    protected lateinit var bitmap: Bitmap
    protected lateinit var model: com.hereams.neverland.gameObjects.model.Item

    @JvmName("getBitmap1")
    fun getBitmap(): Bitmap {
        return bitmap
    }

    @JvmName("setBitmap1")
    fun setBitmap(value: Bitmap) {
        bitmap = value
    }

    @JvmName("getModel1")
    fun getModel(): com.hereams.neverland.gameObjects.model.Item {
        return model
    }

    @JvmName("setModel1")
    fun setModel(value: com.hereams.neverland.gameObjects.model.Item) {
        model = value
    }

    abstract fun draw(rect: Rect, canvas: Canvas?)

}