package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.*
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.Button
import javafx.stage.Stage
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
    private lateinit var añadirRegistrosFinancierosButton: Button

    @FXML
    private lateinit var eliminarRegistrosFinancierosButton: Button

    @FXML
    fun setEmpresa(empresa: Empresa) {
        this.empresa = empresa
    }

    @FXML
    fun initialize() {
        verRegistrosFinancierosButton.setOnAction { navigateTo("/vista/RegistrosFinancieros.fxml") }
        añadirRegistrosFinancierosButton.setOnAction { navigateTo("/vista/NuevoRegistroFinanciero.fxml") }
        eliminarRegistrosFinancierosButton.setOnAction { navigateTo("/vista/EliminarRegistro.fxml") }
    }

    private fun navigateTo(viewPath: String) {
        try {
            val fxmlLoader = FXMLLoader(javaClass.getResource(viewPath))
            fxmlLoader.setControllerFactory { context.getBean(it) }
            val root = fxmlLoader.load<Parent>()

            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()

            // Cierra la vista actual
            (verRegistrosFinancierosButton.scene.window as Stage).close()
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}