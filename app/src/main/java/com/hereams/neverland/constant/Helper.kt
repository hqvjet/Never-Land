package com.hereams.neverland.constant

import android.content.Context
import android.content.res.Resources
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.SurfaceView

class Helper(val view: SurfaceView) {

    private val displayMetrics: DisplayMetrics = Resources.getSystem().displayMetrics

    fun getDeviceWidth(): Int {
        return displayMetrics.widthPixels
    }

    fun getDeviceHeight(): Int{
        return displayMetrics.heightPixels
    }

}