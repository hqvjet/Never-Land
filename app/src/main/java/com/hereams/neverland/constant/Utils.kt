package com.hereams.neverland.constant

import kotlin.math.pow
import kotlin.math.sqrt

class Utils {
    fun getDistanceBetweenPoints(p1x: Float, p1y: Float, p2x: Float, p2y: Float): Float {
        return sqrt((p1x - p2x).pow(2) + (p1y - p2y).pow(2))
    }
}