package com.hereams.neverland.constant

import android.util.TypedValue
import android.view.SurfaceView

class Helper(private val view: SurfaceView) {

    fun toDP(value: Float): Float {
        val density = view.resources.displayMetrics.density
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            value / density,
            view.resources.displayMetrics
        );
    }

}