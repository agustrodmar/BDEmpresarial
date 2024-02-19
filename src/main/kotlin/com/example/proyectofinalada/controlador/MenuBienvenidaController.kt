package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.util.SpringControllerFactory
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller





@Controller
class MenuBienvenidaController(private val context: ApplicationContext) {

    @FXML
    fun handleCrearNuevaBaseDeDatosButtonAction(event: ActionEvent) {
        try {
            // Carga la nueva vista
            val fxmlLoader = FXMLLoader(javaClass.getResource("/vista/NuevaBaseDeDatos.fxml"))
            fxmlLoader.controllerFactory = SpringControllerFactory(context)
            val root = fxmlLoader.load<Parent>()

            // Crea una nueva escena con la nueva vista y la muestra
            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()

            // Cierra la vista actual
            (event.source as Node).scene.window.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    fun handleUsarBaseDeDatosExistenteButtonAction(event: ActionEvent) {
        try {
            // Carga la nueva vista
            val fxmlLoader = FXMLLoader(javaClass.getResource("/vista/SeleccionarEmpresa.fxml"))
            fxmlLoader.controllerFactory = SpringControllerFactory(context)
            val root = fxmlLoader.load<Parent>()

            // Crea una nueva escena con la nueva vista y la muestra
            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()

            // Cierra la vista actual
            (event.source as Node).scene.window.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}



