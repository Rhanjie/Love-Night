package com.rhanjie.lovenight.input

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.input.GestureDetector
import com.badlogic.gdx.math.Vector2
import com.rhanjie.lovenight.gui.Hud
import com.rhanjie.lovenight.worlds.MapScene

class GameplayGestureListener(private val mapScene: MapScene) : GestureDetector.GestureAdapter() {
    private val playerBody = mapScene.player.body
    private val playerPos = playerBody.position

    override fun fling(velocityX: Float, velocityY: Float, button: Int): Boolean { //move finger
        //TODO: Add virtual joystick
        //playerBody.applyLinearImpulse(velocityX, 0f, playerPos.x, playerPos.y, true)

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
        //TODO: Add virtual joystick
        /*if (playerBody.linearVelocity.y == 0f) {
            val force = playerBody.mass * 10 / (1 / 60.0).toFloat()

            playerBody.applyForce(0f, force, playerPos.x, playerPos.y, true)

        }*/

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