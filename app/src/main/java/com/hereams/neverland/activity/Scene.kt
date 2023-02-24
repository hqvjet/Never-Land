package com.hereams.neverland.activity

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hereams.neverland.R
import com.hereams.neverland.gameLoop.GameLoop
import com.hereams.neverland.gameObjects.view.Scene

class Scene: AppCompatActivity() {

    private lateinit var sceneView: Scene
    private lateinit var scene: MediaPlayer
    private lateinit var ingame_activity: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_scene)
        sceneView = findViewById(R.id.scene)
        scene = sceneView.scene
        scene.setOnCompletionListener {
            ingame_activity = Intent(this, InGame::class.java)
            startActivity(ingame_activity)
        }


    }

    // Detect if user exit game
    override fun onDestroy() {
        super.onDestroy()
    }

    // Detect if user switch to another application
    override fun onPause() {
        super.onPause()
        scene.pause()
    }

    override fun onResume() {
        super.onResume()
        scene.start()
    }

    fun skip(view: View) {
        if(scene.isPlaying) {
            scene.seekTo(scene.duration)
        }
    }
}