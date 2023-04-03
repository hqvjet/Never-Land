package com.hereams.neverland.constant

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.SurfaceView

class Helper(val view: SurfaceView) {

    private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics

    fun toDP(value: Float): Float {
        val density = view.resources.displayMetrics.density
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value / density,
            view.resources.displayMetrics
        );
    }

    fun getDeviceWidth(): Int {
        return displayMetrics.widthPixels
    }

    fun getDeviceHeight(): Int{
        return displayMetrics.heightPixels
    }

}