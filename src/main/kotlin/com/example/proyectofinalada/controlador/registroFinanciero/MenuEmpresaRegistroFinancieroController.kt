package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.*
import com.example.proyectofinalada.util.Navigator
import javafx.fxml.FXML
import javafx.scene.control.Button
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller



@Suppress("SpellCheckingInspection")
@Controller
class MenuEmpresaRegistroFinancieroController {

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var empresa: Empresa

    @FXML
    private lateinit var verRegistrosFinancierosButton: Button

    @FXML
    private lateinit var NuevoRegistrosFinancierosButton: Button

    @FXML
    private lateinit var eliminarRegistrosFinancierosButton: Button

    @FXML
    private lateinit var volverAlMenuButton: Button

    @FXML
    fun setEmpresa(empresa: Empresa) {
        this.empresa = empresa
    }

    fun handleVolverAlMenuButtonAction() {
        Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
    }

    @FXML
    fun initialize() {
        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }
        verRegistrosFinancierosButton.setOnAction { Navigator.loadScene("/vista/RegistrosFinancieros.fxml", context) }
        NuevoRegistrosFinancierosButton.setOnAction { Navigator.loadScene("/vista/NuevoRegistroFinanciero.fxml", context) }
        eliminarRegistrosFinancierosButton.setOnAction { Navigator.loadScene("/vista/EliminarRegistro.fxml", context) }
    }
}