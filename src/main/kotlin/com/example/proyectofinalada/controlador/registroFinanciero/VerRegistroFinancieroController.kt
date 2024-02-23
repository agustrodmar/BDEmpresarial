package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import com.example.proyectofinalada.servicio.empresa.EmpresaActualService
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

    @Autowired
    private lateinit var empresaActualService: EmpresaActualService

    @FXML
    private lateinit var registrosFinancierosList: ListView<RegistroFinanciero>

    @FXML
    private lateinit var volverAlMenuButton: Button

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    fun initialize() {
        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }

        // Carga los registros financieros de la empresa seleccionada
        val empresa = empresaActualService.getEmpresa()
        if (empresa != null) {
            val registrosFinancieros = registroFinancieroService.
            encontrarPorEmpresa(empresa)
            registrosFinancierosList.items = FXCollections.observableArrayList(registrosFinancieros)
        }
    }

    fun handleVolverAlMenuButtonAction() {
        Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
    }
}