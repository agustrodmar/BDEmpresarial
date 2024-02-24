package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.servicio.empresa.EmpresaService
import com.example.proyectofinalada.util.Navigator
import javafx.fxml.FXML
import javafx.scene.control.TextField
import org.springframework.stereotype.Controller
import org.springframework.context.ApplicationContext
import org.springframework.beans.factory.annotation.Autowired



/**
 * Controlador para la creación de una nueva base de datos.
 *
 * Este controlador maneja las acciones del usuario en la pantalla de creación de una nueva base de datos,
 * como introducir el nombre de la nueva base de datos y guardar la nueva base de datos.
 *
 * @property empresaService El servicio para interactuar con las empresas.
 */
@Controller
class NuevaBaseDeDatosController(private val empresaService: EmpresaService) {

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var nombreField: TextField

    /**
     * Maneja la acción del botón para volver al menú.
     *
     * Cuando el usuario hace clic en el botón para volver al menú, este método carga la escena del menú de bienvenida.
     */
    fun handleAtrasButtonAction() {
        try {
            Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Maneja la acción del botón para guardar una nueva base de datos.
     *
     * Cuando el usuario hace clic en el botón para guardar una nueva base de datos, este método crea una nueva base de datos
     * con el nombre introducido por el usuario y luego guarda la nueva base de datos.
     */
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