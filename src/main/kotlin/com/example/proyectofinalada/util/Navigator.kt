package com.example.proyectofinalada.util

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import java.io.IOException


/**
 * Objeto para navegar entre las diferentes escenas de la aplicación.
 *
 * Este objeto proporciona métodos para establecer el escenario principal y cargar una nueva escena.
 */
object Navigator {
    private var primaryStage: Stage? = null

    /**
     * Establece el escenario principal de la aplicación.
     *
     * @param stage El escenario principal.
     */
    fun setPrimaryStage(stage: Stage?) {
        primaryStage = stage
    }

    /**
     * Carga una nueva escena en el escenario principal.
     *
     * Este método carga un archivo FXML y lo establece como la escena del escenario principal.
     *
     * @param fxmlPath La ruta al archivo FXML de la escena a cargar.
     * @param context El contexto de la aplicación.
     */
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