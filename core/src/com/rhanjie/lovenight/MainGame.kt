package com.rhanjie.lovenight

import com.badlogic.gdx.Screen
import com.badlogic.gdx.physics.box2d.Box2D
import com.kotcrab.vis.ui.VisUI
import com.rhanjie.lovenight.screens.ScreenGameplay
import ktx.app.KtxGame

class MainGame : KtxGame<Screen>() {
    companion object {
        const val ratio = 1f/100f
    }

    override fun create() {
        super.create()
        VisUI.load()
        Box2D.init()

        this.addScreen(ScreenGameplay())
        this.setScreen<ScreenGameplay>()
    }

    override fun render() {
        super.render()

        //TODO: Other background color
    }

    override fun dispose() {
        VisUI.dispose()
        super.dispose()
    }
}
