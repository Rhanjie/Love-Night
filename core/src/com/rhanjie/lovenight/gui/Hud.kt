package com.rhanjie.lovenight.gui

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.scenes.scene2d.EventListener
import com.badlogic.gdx.scenes.scene2d.InputEvent
import com.badlogic.gdx.scenes.scene2d.InputListener
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.rhanjie.lovenight.MainGame

class Hud(val stage: Stage) {
    private val skin: Skin = Skin().apply {
        this.add("touchpadBackground", Texture("touchpadBackground.png"))
        this.add("touchpadKnob", Texture("touchpadKnob.png"))
    }

    private val style: Touchpad.TouchpadStyle = Touchpad.TouchpadStyle().apply {
        this.background = skin.getDrawable("touchpadBackground")
        this.knob = skin.getDrawable("touchpadKnob")
    }

    val touchpad: Touchpad = Touchpad(10f, style).apply {
        this.x = 100f
        this.y = 80f
    }

    init {
        stage.addActor(touchpad)
    }
}