package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.servicio.EmpresaService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import org.springframework.stereotype.Controller
import java.awt.TextField

/**
 * El controlador que maneja los datos las empresas
 *
 */
@Controller
class EmpresaController(private val empresaService: EmpresaService, private val registroFinancieroController: RegistroFinancieroController) {

    @FXML
    private lateinit var tipoRegistroComboBox: ComboBox<String>

    @FXML
    private lateinit var conceptoField: TextField

    @FXML
    private lateinit var empresaComboBox: ComboBox<String>

    fun initialize() {
        val empresas = empresaService.encontrarTodo()
        val nombresEmpresas = empresas.map { it.nombre }
        empresaComboBox.items = FXCollections.observableArrayList(nombresEmpresas)

        // Añade un listener para manejar los cambios de selección en el ComboBox
        empresaComboBox.valueProperty().addListener { _, _, selectedEmpresa ->
            // Aquí puedes manejar el cambio de selección
            // Por ejemplo, podrías cargar los registros financieros de la empresa seleccionada
            val empresa = empresas.find { it.nombre == selectedEmpresa }
            if (empresa != null) {
                registroFinancieroController.setEmpresa(empresa)
            }
        }
    }
}