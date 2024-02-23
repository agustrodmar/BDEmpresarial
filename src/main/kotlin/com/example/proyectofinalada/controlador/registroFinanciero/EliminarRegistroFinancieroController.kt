package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import com.example.proyectofinalada.util.Navigator
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ListView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller

@Controller
class EliminarRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var registrosAEliminarList: ListView<RegistroFinanciero>

    @FXML
    private lateinit var volverAlMenuButton: Button

    @FXML
    private lateinit var empresa: Empresa


    fun setEmpresa(empresa: Empresa) {
        this.empresa = empresa
    }

    fun handleVolverAlMenuButtonAction() {
        Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
    }

    @FXML
    fun handleEliminarRegistroButtonAction(event: ActionEvent) {
        val registroSeleccionado = registrosAEliminarList.selectionModel.selectedItem

        if (registroSeleccionado != null) {
            // Elimina el registro financiero
            registroFinancieroService.eliminar(registroSeleccionado)
        }
    }

    fun initialize() {

        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }
    }
}