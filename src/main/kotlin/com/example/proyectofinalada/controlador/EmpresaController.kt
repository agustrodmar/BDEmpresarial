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
 * Controlador para la gestión de empresas.
 *
 * Este controlador maneja las acciones del usuario en la pantalla de gestión de empresas,
 * como seleccionar una empresa, cargar la pantalla de registros financieros de una empresa y volver al menú.
 *
 * @property empresaService El servicio para interactuar con las empresas.
 * @property registroFinancieroController El controlador para el menú de registros financieros de una empresa.
 * @property empresaActualService El servicio para gestionar la empresa actualmente seleccionada.
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


    /**
     * Maneja la acción del botón para volver al menú.
     *
     * Cuando el usuario hace clic en el botón para volver al menú, este método carga la escena del menú de bienvenida.
     */
    @FXML
    fun handleAtrasButtonAction() {
        try {
            Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Inicializa el controlador.
     *
     * Este método se llama después de que se ha cargado el archivo FXML. Aquí es donde puedes realizar cualquier
     * inicialización necesaria para tu controlador.
     */
    @FXML
    fun initialize() {
        // Carga las empresas y las añade al ComboBox
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

        // Añade un listener al botón de cargar para cargar la escena de registros financieros de la empresa seleccionada
        cargarButton.setOnAction {
            try {
                Navigator.loadScene("/vista/MenuEmpresaRegistro.fxml", context)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}