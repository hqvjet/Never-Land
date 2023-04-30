package com.hereams.neverland.gameObjects.view.component.item

import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.graphics.Canvas
import com.hereams.neverland.R
import com.hereams.neverland.constant.STEEL_SWORD

class SteelSword(val context: Context, enh_lvl: Int): Weapon() {

    init {

        val bitmapOptions: BitmapFactory.Options = BitmapFactory.Options()

        bitmapOptions.inScaled = false

        model = com.hereams.neverland.gameObjects.model.Weapon(STEEL_SWORD, enh_lvl)

        setModel(model)

        bitmap = BitmapFactory.decodeResource(context.resources, R.drawable.weapon_steel_sword, bitmapOptions)
        bitmap = Bitmap.createScaledBitmap(bitmap, 64, 64, false)

        setBitmap(bitmap)
    }

}