package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.controlador.registroFinanciero.MenuEmpresaRegistroFinancieroController
import com.example.proyectofinalada.servicio.empresa.EmpresaActualService
import com.example.proyectofinalada.servicio.empresa.EmpresaService
import com.example.proyectofinalada.util.Navigator
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import javafx.stage.Stage
import  javafx.scene.control.Button
import org.springframework.stereotype.Controller
import org.springframework.context.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired

/**
 * El controlador que maneja los datos las empresas
 *
 */
@Controller
class EmpresaController(private val empresaService: EmpresaService,
                        private val registroFinancieroController: MenuEmpresaRegistroFinancieroController,
                        private val empresaActualService: EmpresaActualService) {

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    lateinit var primaryStage: Stage

    @FXML
    lateinit var cargarButton: Button

    @FXML
    private lateinit var tipoRegistroComboBox: ComboBox<String>

    @FXML
    private lateinit var empresaComboBox: ComboBox<String>


    @FXML
    fun handleAtrasButtonAction() {
        try {
            Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    fun initialize() {
        val empresas = empresaService.encontrarTodo()
        val nombresEmpresas = empresas.map { it.nombre }
        empresaComboBox.items = FXCollections.observableArrayList(nombresEmpresas)

        // Añade un listener para manejar los cambios de selección en el ComboBox
        empresaComboBox.valueProperty().addListener { _, _, selectedEmpresa ->
            val empresa = empresas.find { it.nombre == selectedEmpresa }
            if (empresa != null) {
                empresaActualService.setEmpresa(empresa)
            }
        }

        cargarButton.setOnAction {
            Navigator.loadScene("/vista/MenuEmpresaRegistro.fxml", context)
        }
    }
}