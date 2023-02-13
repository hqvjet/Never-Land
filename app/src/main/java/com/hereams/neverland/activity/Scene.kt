package com.hereams.neverland.activity

import android.os.Bundle
import android.os.PersistableBundle
import androidx.appcompat.app.AppCompatActivity
import com.hereams.neverland.R
import com.hereams.neverland.gameLoop.GameLoop

class Scene: AppCompatActivity() {

    private lateinit var game_loop: GameLoop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_scene)
//        game_loop = GameLoop()
//        game_loop.start()

    }

    // Detect if user exit game
    override fun onDestroy() {
        super.onDestroy()
        game_loop.is_exit = true
    }

    // Detect if user switch to another application
    override fun onPause() {
        super.onPause()
        game_loop.is_in_game = false
    }
}