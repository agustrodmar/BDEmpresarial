package com.example.proyectofinalada.controlador.registroFinanciero


import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import com.example.proyectofinalada.servicio.empresa.EmpresaActualService
import com.example.proyectofinalada.util.Navigator
import javafx.collections.FXCollections
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ListView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller

/**
 * Controlador para eliminar un registro financiero.
 *
 * Este controlador maneja la acción del usuario de eliminar un registro financiero.
 */
@Controller
class EliminarRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @Autowired
    private lateinit var empresaActualService: EmpresaActualService

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var registrosAEliminarList: ListView<RegistroFinanciero>

    @FXML
    private lateinit var volverAlMenuButton: Button

    /**
     * Inicializa el controlador.
     *
     * Este método se llama después de que se ha cargado el archivo FXML. Aquí es donde puedes realizar cualquier
     * inicialización necesaria para tu controlador.
     */
    @FXML
    fun initialize() {
        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }

        // Carga los registros financieros de la empresa seleccionada
        val empresa = empresaActualService.getEmpresa()
        if (empresa != null) {
            val registrosFinancieros = registroFinancieroService.encontrarPorEmpresa(empresa)
            registrosAEliminarList.items = FXCollections.observableArrayList(registrosFinancieros)
        }
    }

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
     * Maneja la acción del botón para eliminar un registro financiero.
     *
     * Cuando el usuario hace clic en el botón para eliminar un registro financiero, este método elimina el registro
     * financiero seleccionado.
     *
     * @param event El evento de acción.
     */
    @FXML
    fun handleEliminarRegistroButtonAction(event: ActionEvent) {
        val registroSeleccionado = registrosAEliminarList.selectionModel.selectedItem

        if (registroSeleccionado != null) {
            try {
                // Elimina el registro financiero
                registroFinancieroService.eliminar(registroSeleccionado)
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}