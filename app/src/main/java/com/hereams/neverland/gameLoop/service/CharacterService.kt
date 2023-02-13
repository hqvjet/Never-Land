package com.hereams.neverland.gameLoop.service

import com.hereams.neverland.gameObjects.view.CharacterView
import com.hereams.neverland.gameObjects.view.DPadView

public class CharacterService(private val view: CharacterView) {

    private lateinit var controller: DPadView

    public fun addDPad(dpad: DPadView) {
        controller = dpad
    }

    public fun openInventory() {
        //logic
    }

    public fun openSelf() {
        //logic
    }

    public fun interactWithNPC(npc_name: String) {
        //logic
    }

    public fun openOption() {

    }

    public fun combat() {

    }

    public fun die() {

    }

}