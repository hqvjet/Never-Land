package com.hereams.neverland.gameObjects.view.component

import android.app.Activity
import android.content.Context
import android.media.MediaPlayer
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.SurfaceHolder
import android.view.SurfaceView
import android.widget.Button
import com.hereams.neverland.R

class Scene @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
) : SurfaceView(context, attrs, defStyleAttr), SurfaceHolder.Callback {

    public lateinit var scene: MediaPlayer
    private var sf_holder: SurfaceHolder = holder
    private lateinit var button: Button
    val activity = context as Activity

    init {
        sf_holder.addCallback(this)
        scene = MediaPlayer.create(context, R.raw.first_scene)
    }

    override fun surfaceCreated(p0: SurfaceHolder) {
        scene.setDisplay(sf_holder)
        scene.start()
    }

    override fun surfaceChanged(p0: SurfaceHolder, p1: Int, p2: Int, p3: Int) {
    }

    override fun surfaceDestroyed(p0: SurfaceHolder) {
        scene.release()
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        val button: Button = activity.findViewById(R.id.skip_scene)
        button.visibility = Button.VISIBLE
        return super.onTouchEvent(event)
    }

}