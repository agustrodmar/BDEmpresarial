package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.ProyectoFinalAdaApplication
import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.servicio.EmpresaService
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.springframework.stereotype.Controller
import org.springframework.context.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired

@Controller
class NuevaBaseDeDatosController(private val empresaService: EmpresaService) {

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var nombreField: TextField

    fun handleAtrasButtonAction() {
        try {
            // Obtén una referencia a ProyectoFinalAdaApplication
            val app = context.getBean(ProyectoFinalAdaApplication::class.java)

            // Carga la vista de bienvenida
            val fxmlLoader = FXMLLoader(javaClass.getResource("/vista/MenuBienvenida.fxml"))
            fxmlLoader.setControllerFactory { context.getBean(it) }
            val root = fxmlLoader.load<Parent>()

            // Crea una nueva escena con la vista de bienvenida y la muestra
            val stage = Stage()
            stage.scene = Scene(root)
            stage.show()

        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    @FXML
    fun handleGuardarButtonAction() {
        try {
            val nombre = nombreField.text
            val nuevaEmpresa = Empresa(nombre = nombre)
            val savedEmpresa = empresaService.guardar(nuevaEmpresa)
            println("Empresa guardada: $savedEmpresa")
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }
}