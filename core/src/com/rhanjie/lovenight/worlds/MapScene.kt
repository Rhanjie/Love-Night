package com.rhanjie.lovenight.worlds

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.rhanjie.lovenight.MainGame
import com.rhanjie.lovenight.gui.Hud
import com.rhanjie.lovenight.worlds.objects.MapObjectDynamic
import com.rhanjie.lovenight.worlds.objects.Player
import ktx.box2d.body
import ktx.box2d.createWorld
import ktx.box2d.earthGravity
import kotlin.random.Random

class MapScene(val stage: Stage, private val hud: Hud) {
    val background = Group()
    val middleground = Group()
    val foreground = Group()

    val physicalWorld: World = createWorld(earthGravity)
    val debugRenderer = Box2DDebugRenderer()

    var centerPosition = Vector2(stage.viewport.screenWidth / 2f, stage.viewport.screenHeight / 2f)
        private set

    var player = Player(centerPosition, Texture("badlogic.jpg"), physicalWorld, stage.camera as OrthographicCamera)

    private var accumulator = 0f
    private val timeStep = 1f / 60f

    init {
        stage.apply {
            this.addActor(background)
            this.addActor(middleground)
            this.addActor(foreground)

            middleground.addActor(player)
        }

        (stage.camera as OrthographicCamera).zoom *= MainGame.ratio
        stage.camera.update()
        this.createDebugGround()
        this.createDebugObjects()
    }

    fun render() {
        this.handleInput()

        debugRenderer.render(physicalWorld, stage.camera.combined)

        this.doPhysicalStep()
    }

    private fun handleInput() {
        player.currentSpeedX = hud.touchpad.knobPercentX * 300f

        if (hud.touchpad.knobPercentY > 0.5f && player.body.linearVelocity.y == 0f) {
            val pos = player.body.position
            val force = player.body.mass * 10 / (1 / 60.0).toFloat()

            player.body.applyForce(0f, force, pos.x, pos.y, true)
        }
    }

    private fun doPhysicalStep() {
        val frameTime = Math.min(Gdx.graphics.deltaTime, 0.25f)
        accumulator += frameTime

        while (accumulator >= timeStep) {
            physicalWorld.step(timeStep, 6, 2)
            accumulator -= timeStep
        }
    }

    private fun createDebugGround() {
        var testGround = physicalWorld.body {
            box(2500f * MainGame.ratio, 10f * MainGame.ratio) {}

            this.position.set(0f * MainGame.ratio, 40f * MainGame.ratio)
        }

        var testFallDefender = physicalWorld.body {
            box(10f * MainGame.ratio, 30f * MainGame.ratio) {}

            this.position.set(-2500f * MainGame.ratio / 2f, 40f * MainGame.ratio)
        }
    }

    private fun createDebugObjects() {
        var randomPosition = centerPosition.add(Vector2(Random.nextFloat() * 500, Random.nextFloat() * 500))

        val object1 = MapObjectDynamic(randomPosition, Texture("badlogic.jpg"), physicalWorld)
        randomPosition = centerPosition.add(Vector2(Random.nextFloat() * 500, Random.nextFloat() * 500))
        val object2 = MapObjectDynamic(randomPosition, Texture("badlogic.jpg"), physicalWorld)
        randomPosition = centerPosition.add(Vector2(Random.nextFloat() * 500, Random.nextFloat() * 500))
        val object3 = MapObjectDynamic(randomPosition, Texture("badlogic.jpg"), physicalWorld)

        stage.apply {
            middleground.addActor(object1)
            middleground.addActor(object2)
            middleground.addActor(object3)
        }
    }
}