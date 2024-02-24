package com.example.proyectofinalada.util

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import java.io.IOException


object Navigator {
    private var primaryStage: Stage? = null

    fun setPrimaryStage(stage: Stage?) {
        primaryStage = stage
    }

    fun loadScene(fxmlPath: String?, context: ApplicationContext?) {
        try {
            val fxmlLoader = FXMLLoader()
            fxmlLoader.classLoader = this.javaClass.classLoader
            fxmlLoader.location = Navigator::class.java.getResource(fxmlPath)
            fxmlLoader.setControllerFactory { clazz -> context!!.getBean(clazz) }
            val root: Parent = fxmlLoader.load()
            primaryStage!!.scene = Scene(root)
            primaryStage!!.show()
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}