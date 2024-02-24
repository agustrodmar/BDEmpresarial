package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.util.Navigator
import javafx.fxml.FXML
import javafx.scene.control.Button
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller


/**
 * Controlador para el menú de registros financieros de una empresa.
 *
 * Este controlador maneja las acciones del usuario en el menú de registros financieros,
 * como ver, añadir y eliminar registros financieros.
 */
@Suppress("SpellCheckingInspection")
@Controller
class MenuEmpresaRegistroFinancieroController {

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var verRegistrosFinancierosButton: Button

    @FXML
    private lateinit var nuevoRegistrosFinancierosButton: Button

    @FXML
    private lateinit var eliminarRegistrosFinancierosButton: Button

    @FXML
    private lateinit var volverAlMenuButton: Button


    /**
     * Maneja la acción del botón para volver al menú.
     *
     * Cuando el usuario hace clic en el botón para volver al menú, este método carga la escena del menú de bienvenida.
     */
    fun handleVolverAlMenuButtonAction() {
        try {
            Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Inicializa el controlador.
     *
     * Este método se llama después de que se ha cargado el archivo FXML. Aquí es donde se realiza cualquier
     * inicialización necesaria para el controlador.
     */
    @FXML
    fun initialize() {
        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }
        verRegistrosFinancierosButton.setOnAction { Navigator.loadScene("/vista/RegistrosFinancieros.fxml", context) }
        nuevoRegistrosFinancierosButton.setOnAction { Navigator.loadScene("/vista/NuevoRegistroFinanciero.fxml", context) }
        eliminarRegistrosFinancierosButton.setOnAction { Navigator.loadScene("/vista/EliminarRegistro.fxml", context) }
    }
}