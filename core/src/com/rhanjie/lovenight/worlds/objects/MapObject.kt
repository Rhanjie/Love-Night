package com.rhanjie.lovenight.worlds.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Actor
import com.rhanjie.lovenight.MainGame
import ktx.box2d.body
import ktx.box2d.box
import kotlin.random.Random

open class MapObject(protected val spawnPosition: Vector2, texture: Texture, physicalWorld: World) : Actor() {
    protected val sprite = Sprite(texture)

    val body: Body

    init {
        width = texture.width.toFloat() * MainGame.ratio
        height = texture.height.toFloat() * MainGame.ratio
        spawnPosition.scl(MainGame.ratio)

        body = physicalWorld.body {
            this.position.set(spawnPosition)
            this.type = BodyDef.BodyType.StaticBody

            /*box(width, height) {
                this.density = 40f
                this.friction = 0.5f
                this.restitution = 0f
            }*/
        }
    }

    fun addBoxCollider(width: Float = this.width, height: Float = this.height) {
        body.box(width, height) {
            this.density = 40f
            this.friction = 0.5f
            this.restitution = 0f
        }
    }

    protected open fun update(delta: Float) {
        x = body.position.x - width / 2f
        y = body.position.y - height / 2f
    }

    override fun act(delta: Float) {
        super.act(delta)

        this.update(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        sprite.rotation = body.angle * 180 / Math.PI.toFloat()
        sprite.setOrigin(width / 2f, height / 2f)
        sprite.setPosition(x, y)
        sprite.setSize(width, height)
        sprite.draw(batch)
    }
}