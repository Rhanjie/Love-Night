package com.rhanjie.lovenight.worlds.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.World
import com.rhanjie.lovenight.MainGame
import kotlin.random.Random

class Player(spawnPosition: Vector2, texture: Texture, physicalWorld: World, var camera: OrthographicCamera)
    : MapObjectDynamic(spawnPosition, texture, physicalWorld) {

    var maxSpeed = 4f
    var currentSpeedX = 0f

    init {
        body.isFixedRotation = true
        //body.applyTorque(1f, true)
    }

    override fun update(delta: Float) {
        super.update(delta)

        sprite.color = Color(Random.nextFloat(), Random.nextFloat(), Random.nextFloat(), 1f)

        this.updatePosition(delta)
    }

    private fun updatePosition(delta: Float) {
        body.linearVelocity = Vector2(currentSpeedX * delta, body.linearVelocity.y)

        this.limitVelocity()
        this.teleportWhenFell()

        camera.position.set(body.position.x, body.position.y , 0f)
        camera.update()
    }

    private fun limitVelocity() {
        when {
            body.linearVelocity.x > maxSpeed  -> body.linearVelocity.x = maxSpeed
            body.linearVelocity.x < -maxSpeed -> body.linearVelocity.x = -maxSpeed
        }

        if (body.linearVelocity.y > 10f)
            body.setLinearVelocity(body.linearVelocity.x, 10f)
    }

    private fun teleportWhenFell() {
        if (body.position.y <= -1000f * MainGame.ratio) {
            body.setTransform(spawnPosition, 0f)
            body.setLinearVelocity(0f, 0f)
            body.angularVelocity = 0f
        }
    }
}