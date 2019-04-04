package com.rhanjie.lovenight.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.rhanjie.lovenight.MainGame
import com.rhanjie.lovenight.gui.Hud
import com.rhanjie.lovenight.worlds.MapScene
import com.rhanjie.lovenight.worlds.objects.MapObjectDynamic
import kotlin.random.Random

class GameplayGestureListener(private val mapScene: MapScene) : GestureDetector.GestureAdapter() {
    private val playerBody = mapScene.player.body
    private val playerPos = playerBody.position

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean { //move finger


        return true
    }

    override fun zoom(initialDistance: Float, distance: Float): Boolean {
        return true
    }

    override fun pan(x: Float, y: Float, deltaX: Float, deltaY: Float): Boolean {
        return true
    }

    override fun pinchStop() {
    }

    override fun tap(x: Float, y: Float, count: Int, button: Int): Boolean {
        var tapPosition = Vector3(x, y, 0f)
        mapScene.stage.camera.unproject(tapPosition)

        println("position: $y ${Vector2(tapPosition.x / MainGame.ratio, tapPosition.y / MainGame.ratio)}")
        val newObject = MapObjectDynamic(Vector2(tapPosition.x / MainGame.ratio, tapPosition.y / MainGame.ratio), Texture("tiles.png"), mapScene.physicalWorld)

        mapScene.stage.addActor(newObject)

        return true
    }

    override fun panStop(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun longPress(x: Float, y: Float): Boolean {
        return true
    }

    override fun touchDown(x: Float, y: Float, pointer: Int, button: Int): Boolean {
        return true
    }

    override fun pinch(initialPointer1: Vector2?, initialPointer2: Vector2?, pointer1: Vector2?, pointer2: Vector2?): Boolean {
        return true
    }
}