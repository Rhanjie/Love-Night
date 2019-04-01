package com.rhanjie.lovenight.worlds.objects

import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Batch
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.scenes.scene2d.Actor
import kotlin.random.Random

open class MapObject(texture: Texture) : Actor() {
    protected val sprite = Sprite(texture)

    protected open fun update(delta: Float) {

    }

    override fun act(delta: Float) {
        super.act(delta)

        this.update(delta)
    }

    override fun draw(batch: Batch?, parentAlpha: Float) {
        super.draw(batch, parentAlpha)

        //sprite.setPosition(0f, 0f)
        //sprite.draw(batch)
    }
}