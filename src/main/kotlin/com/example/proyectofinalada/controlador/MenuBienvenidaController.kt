package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.util.Navigator
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
            Navigator.loadScene("/vista/NuevaBaseDeDatos.fxml", context)
            (event.source as Node).scene.window.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    fun handleUsarBaseDeDatosExistenteButtonAction(event: ActionEvent) {
        try {
            Navigator.loadScene("/vista/SeleccionarEmpresa.fxml", context)
            (event.source as Node).scene.window.hide()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}