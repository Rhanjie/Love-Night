package com.rhanjie.lovenight.worlds.objects

import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.Body
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.World
import com.rhanjie.lovenight.MainGame
import ktx.box2d.body
import ktx.box2d.box

open class MapObjectDynamic(spawnPosition: Vector2, texture: Texture, physicalWorld: World)
    : MapObject(spawnPosition, texture, physicalWorld) {

    init {
        body.type = BodyDef.BodyType.DynamicBody

        body.box(width, height) {
            this.density = 40f
            this.friction = 0.5f
            this.restitution = 0f
        }
    }

    override fun update(delta: Float) {
        super.update(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)
    }
}