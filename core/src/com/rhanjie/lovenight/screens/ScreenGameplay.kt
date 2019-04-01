package com.rhanjie.lovenight.screens

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.InputMultiplexer
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.ui.Touchpad
import com.badlogic.gdx.utils.viewport.ScreenViewport
import com.rhanjie.lovenight.MainGame
import com.rhanjie.lovenight.gui.Hud
import com.rhanjie.lovenight.input.GameplayGestureListener
import com.rhanjie.lovenight.worlds.MapScene
import ktx.actors.centerPosition
import ktx.app.KtxScreen

class ScreenGameplay : KtxScreen {
    private val batch = SpriteBatch()
    private val camera = OrthographicCamera()

    var stage = Stage(ScreenViewport(camera), batch)
    var hud = Hud(Stage(ScreenViewport()))

    private val mapScene = MapScene(stage, hud)

    private val gestureListener = GameplayGestureListener(mapScene)
    private val inputMultiplexer = InputMultiplexer().apply {
        this.addProcessor(stage)
        this.addProcessor(hud.stage)
        this.addProcessor(GestureDetector(gestureListener))

        Gdx.input.inputProcessor = this
    }

    private fun update(delta: Float) {
        stage.act(delta)
        hud.stage.act(delta)
    }

    override fun render(delta: Float) {
        this.update(delta)

        stage.draw()
        hud.stage.draw()
        mapScene.render()
    }

    override fun dispose() {
        stage.dispose()
        batch.dispose()
    }
}