package com.rhanjie.lovenight.desktop

import com.badlogic.gdx.backends.lwjgl.LwjglApplication
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration
import com.rhanjie.lovenight.MainGame

object DesktopLauncher {
    @JvmStatic
    fun main(arg: Array<String>) {
        val config = LwjglApplicationConfiguration().apply {
            this.width = 1920
            this.height = 1080
        }

        LwjglApplication(MainGame(), config)
    }
}
