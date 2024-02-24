package com.example.proyectofinalada.controlador.registroFinanciero


import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import com.example.proyectofinalada.servicio.empresa.EmpresaActualService
import com.example.proyectofinalada.util.Navigator
import javafx.application.Platform
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ListView
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.awt.Desktop
import java.io.File
import java.io.IOException
import java.io.PrintWriter
import java.net.URI
import java.text.SimpleDateFormat
import java.util.*


/**
 * Controlador para la visualización de registros financieros.
 *
 * Este controlador maneja las acciones del usuario en la pantalla de visualización de registros financieros,
 * como cargar los registros financieros de la empresa seleccionada y generar una vista HTML de los registros.
 */
@Controller
class VerRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @Autowired
    private lateinit var empresaActualService: EmpresaActualService

    @FXML
    private lateinit var registrosFinancierosList: ListView<RegistroFinanciero>

    @FXML
    private lateinit var volverAlMenuButton: Button

    @Autowired
    private lateinit var context: ApplicationContext


    /**
     * Inicializa el controlador.
     *
     * Este método se llama después de que se ha cargado el archivo FXML. Aquí es donde se realiza cualquier
     * inicialización necesaria para el controlador.
     */
    @FXML
    fun initialize() {
        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }

        // Carga los registros financieros de la empresa seleccionada
        val empresa = empresaActualService.getEmpresa()
        if (empresa != null) {
            try {
                val registrosFinancieros = registroFinancieroService.encontrarPorEmpresa(empresa)
                registrosFinancierosList.items = FXCollections.observableArrayList(registrosFinancieros)
            } catch (e: Exception) {
                e.printStackTrace()
            }
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
     * Genera una vista HTML de los registros financieros.
     *
     * Cuando el usuario hace clic en el botón para generar la vista HTML, este método crea una nueva vista HTML de los
     * registros financieros de la empresa seleccionada y la abre en el navegador predeterminado, para ello se usa
     * un procedimiento multihilo para compaginar JavaFX y ThymeLeaf en sus distintos ciclos de vida.
     */
    @FXML
    fun generateHtmlView() {
        Thread {
            val templateResolver = ClassLoaderTemplateResolver()
            templateResolver.suffix = ".html"
            templateResolver.templateMode = TemplateMode.HTML

            val templateEngine = TemplateEngine()
            templateEngine.setTemplateResolver(templateResolver)

            val context = Context()
            val empresa = empresaActualService.getEmpresa()
            if (empresa != null) {
                try {
                    val registrosFinancieros = registroFinancieroService.encontrarPorEmpresa(empresa)
                    context.setVariable("registrosFinancieros", registrosFinancieros)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }

            val html = templateEngine.process("registrosFinancieros", context)

            // Guarda la cadena HTML en un archivo con un nombre único
            val timestamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
            val filename = "registrosFinancieros$timestamp.html"
            val file = File(filename)
            PrintWriter(file).use { out -> out.println(html) }

            // Abre el archivo en el navegador predeterminado
            Platform.runLater {
                val url = file.toURI().toString()

                if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
                    Desktop.getDesktop().browse(URI(url))
                } else {
                    val runtime = Runtime.getRuntime()
                    try {
                        val os = System.getProperty("os.name").lowercase(Locale.getDefault())
                        if (os.contains("win")) {
                            runtime.exec("cmd /c start $url")
                        } else if (os.contains("mac")) {
                            runtime.exec("open $url")
                        } else if (os.contains("nix") || os.contains("nux")) {
                            runtime.exec("xdg-open $url")
                        }
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }.start()
    }
}