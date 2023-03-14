package com.hereams.neverland.gameObjects.view.component

import android.content.Context
import android.content.res.ColorStateList
import android.graphics.*
import android.util.AttributeSet
import android.view.View
import android.widget.FrameLayout
import android.widget.ProgressBar
import com.hereams.neverland.R

class InfoBox @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : FrameLayout(context, attrs, defStyleAttr) {

//    private lateinit var

    private lateinit var hp_bar: ProgressBar
    private lateinit var mp_bar: ProgressBar
    private lateinit var exp_bar: ProgressBar

    private lateinit var bitmap: Bitmap

    init {
        setBackgroundColor(Color.TRANSPARENT)

        hp_bar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        hp_bar.max = 100
        hp_bar.progress = 90
        hp_bar.progressTintList = ColorStateList.valueOf(Color.RED)

        mp_bar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        mp_bar.max = 100
        mp_bar.progress = 90
        mp_bar.progressTintList = ColorStateList.valueOf(Color.BLUE)

        exp_bar = ProgressBar(context, null, android.R.attr.progressBarStyleHorizontal)
        exp_bar.max = 100
        exp_bar.progress = 90
        exp_bar.progressTintList = ColorStateList.valueOf(Color.GREEN)

        // set the layout parameters for the progress bars
        val params1 = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params1.topMargin = 25
        hp_bar.layoutParams = params1

        val params2 = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params2.topMargin = 50
        mp_bar.layoutParams = params2

        val params3 = LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT)
        params3.topMargin = 75
        exp_bar.layoutParams = params3

        // add the progress bars to the view group
        addView(hp_bar)
        addView(mp_bar)
        addView(exp_bar)

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
//        canvas.drawBitmap(bitmap, 0f, 0f, null)
    }

}