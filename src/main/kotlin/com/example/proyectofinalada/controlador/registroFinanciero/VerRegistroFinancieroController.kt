package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import com.example.proyectofinalada.util.Navigator
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ListView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller

@Controller
class VerRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @FXML
    private lateinit var registrosFinancierosList: ListView<RegistroFinanciero>

    @FXML
    private lateinit var volverAlMenuButton: Button

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var empresa: Empresa


    fun setEmpresa(empresa: Empresa) {
        this.empresa = empresa
    }
    fun handleVolverAlMenuButtonAction() {
        Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
    }

    @FXML
    fun handleVerRegistrosFinancierosButtonAction() {
        val registrosFinancieros = registroFinancieroService.encontrarTodo()

        // Añade los registros financieros al ListView
        registrosFinancierosList.items = FXCollections.observableArrayList(registrosFinancieros)
    }

    @FXML
    fun initialize() {
        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }
    }
}