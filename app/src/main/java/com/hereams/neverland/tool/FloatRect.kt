package com.hereams.neverland.tool

import android.graphics.Rect

class FloatRect(var left: Float, var top: Float, var right: Float, var bottom: Float) {
    fun intersects(other: Rect): Boolean {
        return left < other.right && right > other.left && top < other.bottom && bottom > other.top
    }
}