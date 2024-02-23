package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.ProyectoFinalAdaApplication
import com.example.proyectofinalada.controlador.registroFinanciero.MenuEmpresaRegistroFinancieroController
import com.example.proyectofinalada.controlador.registroFinanciero.NuevoRegistroFinancieroController
import com.example.proyectofinalada.servicio.EmpresaService
import com.example.proyectofinalada.util.Navigator
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
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
class EmpresaController(private val empresaService: EmpresaService, private val registroFinancieroController: MenuEmpresaRegistroFinancieroController) {

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
            // manejar el cambio de selección
            // cargar los registros financieros de la empresa seleccionada
            val empresa = empresas.find { it.nombre == selectedEmpresa }
            if (empresa != null) {
                // Obtén una referencia a la instancia de MenuEmpresaRegistroFinancieroController
                val menuEmpresaRegistroFinancieroController = context.getBean(MenuEmpresaRegistroFinancieroController::class.java)
                menuEmpresaRegistroFinancieroController.setEmpresa(empresa)
            }
        }

        cargarButton.setOnAction {
            Navigator.loadScene("/vista/MenuEmpresaRegistro.fxml", context)
        }
    }
}