package com.example.proyectofinalada.controlador

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.stereotype.Controller

@Controller
class NuevaBaseDeDatosController {

    @FXML
    protected fun handleCrearNuevaBaseDeDatosButtonAction(event: ActionEvent) {
        // Carga la nueva vista
        val fxmlLoader = FXMLLoader(javaClass.getResource("/vista/NuevaEmpresa.fxml"))

        val root = fxmlLoader.load<Parent>()

        // Crea una nueva escena con la nueva vista y la muestra
        val stage = Stage()
        stage.scene = Scene(root)
        stage.show()
    }
}