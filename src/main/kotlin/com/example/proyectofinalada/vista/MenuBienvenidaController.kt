package com.example.proyectofinalada.vista

import javafx.event.ActionEvent
import javafx.fxml.FXML


class MenuBienvenidaController {
    @FXML
    protected fun handleCrearNuevaBaseDeDatosButtonAction(event: ActionEvent?) {
        println("Crear nueva base de datos")
    }

    @FXML
    protected fun handleUsarBaseDeDatosExistenteButtonAction(event: ActionEvent?) {

        println("Usar base de datos existente")
    }
}
