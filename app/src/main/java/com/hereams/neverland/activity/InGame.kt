package com.hereams.neverland.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.hereams.neverland.R
import com.hereams.neverland.gameObjects.view.CharacterView
import com.hereams.neverland.gameObjects.view.DPadView

class InGame : AppCompatActivity() {

    private lateinit var character_view: CharacterView
    private lateinit var dpad_view: DPadView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()
        setContentView(R.layout.activity_in_game)

        character_view = findViewById(R.id.character)
        dpad_view = findViewById(R.id.dpad)

        dpad_view.addCharacter(character_view)

    }
}