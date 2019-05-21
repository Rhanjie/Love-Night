package com.rhanjie.lovenight.worlds

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.math.Vector3
import com.badlogic.gdx.physics.box2d.BodyDef
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer
import com.badlogic.gdx.physics.box2d.World
import com.badlogic.gdx.scenes.scene2d.Group
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Image
import com.rhanjie.lovenight.MainGame
import com.rhanjie.lovenight.gui.Hud
import com.rhanjie.lovenight.worlds.objects.MapObjectDynamic
import com.rhanjie.lovenight.worlds.objects.Player
import ktx.box2d.body
import ktx.box2d.createWorld
import ktx.box2d.earthGravity
import kotlin.random.Random

class MapScene(val stage: Stage, private val hud: Hud) {
    private val backLayer = Group()
    private val middleLayer = Group()
    private val foreLayer = Group()

    private val background: Image = Image(Texture("background.png")).apply {
        this.width *= MainGame.ratio
        this.height *= MainGame.ratio
    }

    val physicalWorld: World = createWorld(earthGravity)
    private val debugRenderer = Box2DDebugRenderer()

    private var centerPosition = Vector2(stage.viewport.screenWidth / 2f, stage.viewport.screenHeight / 2f)

    var player = Player(centerPosition, Texture("badlogic.jpg"), physicalWorld, stage.camera as OrthographicCamera)

    private var accumulator = 0f
    private val timeStep = 1f / 60f

    init {
        stage.apply {
            this.addActor(backLayer)
            this.addActor(middleLayer)
            this.addActor(foreLayer)

            middleLayer.addActor(player)
            backLayer.addActor(background)
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

        val centerWorldScreen = Vector2(stage.viewport.screenWidth * MainGame.ratio / 2f, stage.viewport.screenHeight * MainGame.ratio / 2f)
        backLayer.setPosition(player.body.position.x - centerWorldScreen.x, player.body.position.y - centerWorldScreen.y)
    }

    private fun handleInput() {
        player.currentSpeedX = hud.touchpad.knobPercentX * 300f

        //println(player.body.linearVelocity.y)
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
            box(2500f * MainGame.ratio, 100f * MainGame.ratio) {
                this.density = 40f
                this.friction = 0.5f
                this.restitution = 0f
            }

            this.position.set(0f * MainGame.ratio, 40f * MainGame.ratio)
        }

        var testFallDefender = physicalWorld.body {
            box(10f * MainGame.ratio, 200f * MainGame.ratio) {
                this.density = 40f
                this.friction = 1f
                this.restitution = 0f
            }

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
            middleLayer.addActor(object1)
            middleLayer.addActor(object2)
            middleLayer.addActor(object3)
        }
    }
}