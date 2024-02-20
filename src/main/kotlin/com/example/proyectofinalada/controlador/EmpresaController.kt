package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.servicio.EmpresaService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.ComboBox
import javafx.stage.Stage
import  javafx.scene.control.Button
import org.springframework.stereotype.Controller
import java.awt.TextField

/**
 * El controlador que maneja los datos las empresas
 *
 */
@Controller
class EmpresaController(private val empresaService: EmpresaService, private val registroFinancieroController: RegistroFinancieroController) {

    @FXML
    lateinit var primaryStage: Stage

    @FXML
    lateinit var cargarButton: Button

    @FXML
    private lateinit var tipoRegistroComboBox: ComboBox<String>

    @FXML
    private lateinit var conceptoField: TextField

    @FXML
    private lateinit var empresaComboBox: ComboBox<String>


    @FXML
    fun initialize() {
    }
}