package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ListView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class VerRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @FXML
    private lateinit var registrosFinancierosList: ListView<RegistroFinanciero>

    @FXML
    fun handleVerRegistrosFinancierosButtonAction() {
        val registrosFinancieros = registroFinancieroService.encontrarTodo()

        // Añade los registros financieros al ListView
        registrosFinancierosList.items = FXCollections.observableArrayList(registrosFinancieros)
    }
}