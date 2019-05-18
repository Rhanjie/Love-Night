package com.rhanjie.lovenight.worlds.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.rhanjie.lovenight.MainGame
import ktx.box2d.body

open class MapObjectDynamic(protected val spawnPosition: Vector2, texture: Texture, physicalWorld: World)
    : MapObject(texture) {

    val body: Body

    init {
        width = texture.width.toFloat() * MainGame.ratio
        height = texture.height.toFloat() * MainGame.ratio
        spawnPosition.scl(MainGame.ratio)

        body = physicalWorld.body {
            this.position.set(spawnPosition)
            this.type = BodyDef.BodyType.DynamicBody

            box(width, height) {
                this.density = 40f
                this.friction = 1f
                this.restitution = 0f
            }
        }
    }

    override fun update(delta: Float) {
        super.update(delta)

        x = body.position.x - width / 2f
        y = body.position.y - height / 2f
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