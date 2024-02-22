package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.ListView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class EliminarRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @FXML
    private lateinit var registrosAEliminarList: ListView<RegistroFinanciero>

    @FXML
    fun handleEliminarRegistroButtonAction(event: ActionEvent) {
        val registroSeleccionado = registrosAEliminarList.selectionModel.selectedItem

        if (registroSeleccionado != null) {
            // Elimina el registro financiero
            registroFinancieroService.eliminar(registroSeleccionado)
        }
    }
}