package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.ProyectoFinalAdaApplication
import com.example.proyectofinalada.servicio.EmpresaService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Node
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.stage.Stage
import org.springframework.stereotype.Controller
import java.awt.event.ActionEvent

@Controller
class SeleccionarEmpresaController(private val empresaService: EmpresaService, private val registroFinancieroController: RegistroFinancieroController) {

    @FXML
    lateinit var cargarButton: Button

    @FXML
    private lateinit var empresaComboBox: ComboBox<String>

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
                registroFinancieroController.setEmpresa(empresa)
            }
        }

        cargarButton.setOnAction {
            val fxmlLoader = FXMLLoader(javaClass.getResource("/vista/MenuEmpresaRegistro.fxml"))
            val root = fxmlLoader.load<Parent>()
            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()
        }
    }
}