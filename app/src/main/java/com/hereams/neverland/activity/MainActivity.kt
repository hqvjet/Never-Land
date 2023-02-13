package com.hereams.neverland.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.hereams.neverland.R


class MainActivity : AppCompatActivity() {

    private lateinit var scene_intent: Intent
    private lateinit var scene: Scene

    // run first
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        supportActionBar?.hide()

        setContentView(R.layout.activity_main)
    }



    fun startGame(view: View) {
        println("sd,mddddddddddddddddddddddddddddddddd")
        scene_intent = Intent(this, Scene::class.java)
        startActivity(scene_intent)

    }

}