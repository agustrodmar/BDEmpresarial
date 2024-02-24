package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.util.Navigator
import javafx.event.ActionEvent
import javafx.fxml.FXML
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller


/**
 * Controlador para el menú de bienvenida.
 *
 * Este controlador maneja las acciones del usuario en la pantalla de bienvenida,
 * como crear una nueva base de datos, usar una base de datos existente y ajustar la base de datos.
 *
 * @property context El contexto de la aplicación.
 */
@Controller
class MenuBienvenidaController(private val context: ApplicationContext) {


    /**
     * Maneja la acción del botón para crear una nueva base de datos.
     *
     * Cuando el usuario hace clic en el botón para crear una nueva base de datos, este método carga la escena de creación de una nueva base de datos.
     *
     * @param event El evento de acción.
     */
    @FXML
    fun handleCrearNuevaBaseDeDatosButtonAction(event: ActionEvent) {
        try {
            Navigator.loadScene("/vista/NuevaBaseDeDatos.fxml", context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Maneja la acción del botón para usar una base de datos existente.
     *
     * Cuando el usuario hace clic en el botón para usar una base de datos existente, este método carga la escena de selección de empresa.
     *
     * @param event El evento de acción.
     */
    @FXML
    fun handleUsarBaseDeDatosExistenteButtonAction(event: ActionEvent) {
        try {
            Navigator.loadScene("/vista/SeleccionarEmpresa.fxml", context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}