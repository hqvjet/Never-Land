package com.hereams.neverland.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.hereams.neverland.gameObjects.layout.GameLayout

class InGame : AppCompatActivity() {

    private lateinit var game_layout: GameLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        val window: Window = window
        window.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN
        )

        game_layout = GameLayout(this)

        setContentView(game_layout)

    }
}