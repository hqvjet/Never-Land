package com.hereams.neverland.gameObjects.view.layout

import android.content.Context
import android.graphics.PointF
import android.graphics.Rect
import android.util.DisplayMetrics
import android.view.WindowManager
import android.widget.FrameLayout
import com.hereams.neverland.constant.CIRCLE_RADIUS
import com.hereams.neverland.gameObjects.Game
import com.hereams.neverland.gameObjects.view.component.*
import com.hereams.neverland.gameObjects.view.component.inventory.InventoryButton
import com.hereams.neverland.gameObjects.view.component.inventory.InventoryView

class GameLayout(context: Context) : FrameLayout(context) {

    private lateinit var game: Game
    private lateinit var attack_button: AttackButtonView
    private lateinit var dpad: DPadView
    private lateinit var character: CharacterView
    private lateinit var inventory_button: InventoryButton

    //features
    private lateinit var inventory_view: InventoryView
//    private lateinit var self

    init {

        val layoutParams_wrap = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
        val layoutParams_full = LayoutParams(
            LayoutParams.MATCH_PARENT,
            LayoutParams.MATCH_PARENT
        )//init character, info box and controller
        val displayMetrics = DisplayMetrics()
        (context.getSystemService(Context.WINDOW_SERVICE) as WindowManager).defaultDisplay.getMetrics(
            displayMetrics
        )
        val screen: PointF =
            PointF(displayMetrics.widthPixels.toFloat(), displayMetrics.heightPixels.toFloat())

        //init character, info box and controller
        dpad = DPadView(
            PointF(
                230f, 800f
            ),
            80f, 200f
        )

        inventory_view = InventoryView(
            this.context,
            screen
        )

        inventory_button = InventoryButton(
            this,
            inventory_view,
            PointF((screen.x * 0.75).toFloat(), (screen.y * 0.8).toFloat()),
            PointF(10f, 10f),
            PointF(50f, 50f),
            50f
        )

        character = CharacterView(
            context,
            PointF(
                displayMetrics.widthPixels.toFloat() / 2f,
                displayMetrics.heightPixels.toFloat() / 2f
            ),
            CIRCLE_RADIUS,
            dpad,
            inventory_view.getModel()
        )

        inventory_view.setOwner(character)

        attack_button = AttackButtonView(
            this.context,
            //view position
            PointF(
                (screen.x * 0.85).toFloat(), (screen.y * 0.7).toFloat()
            ),
            //view width, height
            PointF(10f, 10f),
            //circle's center
            PointF(
                100f, 100f
            ),
            100f,
            character
        )

        game = Game(this.context, dpad, character, attack_button)

        addView(game, layoutParams_full)
        addView(inventory_button, layoutParams_wrap)
        addView(attack_button, layoutParams_wrap)
    }

}