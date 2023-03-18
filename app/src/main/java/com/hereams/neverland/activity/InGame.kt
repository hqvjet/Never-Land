package com.hereams.neverland.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hereams.neverland.R
import com.hereams.neverland.gameLoop.GameLoop
import com.hereams.neverland.gameObjects.view.component.CharacterView
import com.hereams.neverland.gameObjects.view.component.DPadView

class InGame : AppCompatActivity() {

    private lateinit var character_view: CharacterView
    private lateinit var dpad_view: DPadView
    private lateinit var game_loop: GameLoop

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_in_game)

        character_view = findViewById(R.id.character)
        dpad_view = findViewById(R.id.dpad)
        dpad_view.addCharacter(character_view)

        game_loop = GameLoop(character_view, dpad_view)

    }
}