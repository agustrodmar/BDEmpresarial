package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.*
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import com.example.proyectofinalada.servicio.empresa.EmpresaActualService
import com.example.proyectofinalada.util.Navigator
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Controller
import java.math.BigDecimal



/**
 * Controlador para la creación de un nuevo registro financiero.
 *
 * Este controlador maneja las acciones del usuario en la pantalla de creación de un nuevo registro financiero,
 * como seleccionar el tipo de registro, introducir el concepto y la cantidad, y guardar el nuevo registro.
 */
/**
 * Controlador para la creación de un nuevo registro financiero.
 *
 * Este controlador maneja las acciones del usuario en la pantalla de creación de un nuevo registro financiero,
 * como seleccionar el tipo de registro, introducir el concepto y la cantidad, y guardar el nuevo registro.
 */
@Controller
class NuevoRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @Autowired
    private lateinit var empresaActualService: EmpresaActualService

    @FXML
    private lateinit var tipoRegistroComboBox: ComboBox<String>

    @FXML
    private lateinit var conceptoField: TextField

    @FXML
    private lateinit var cantidadField: TextField

    @Autowired
    private lateinit var context: ApplicationContext

    @FXML
    private lateinit var volverAlMenuButton: Button

    /**
     * Inicializa el controlador.
     *
     * Este método se llama después de que se ha cargado el archivo FXML. Aquí es donde puedes realizar cualquier
     * inicialización necesaria para tu controlador.
     */
    fun initialize() {
        volverAlMenuButton.setOnAction { handleVolverAlMenuButtonAction() }
        tipoRegistroComboBox.items = FXCollections.observableArrayList(
            "Inmovilizado intangible",
            "Inmovilizado material",
            "Inmovilizado financiero",
            "Amortizaciones",
            "Existencias",
            "Realizable",
            "Disponible o efectivo",
            "Capital",
            "Reservas",
            "Resultado del ejercicio",
            "Subvenciones",
            "Pasivo No Corriente",
            "Pasivo Corriente"
        )
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
     * Maneja la acción del botón para guardar un nuevo registro financiero.
     *
     * Cuando el usuario hace clic en el botón para guardar un nuevo registro financiero, este método crea un nuevo
     * registro financiero basado en el tipo seleccionado y los datos introducidos por el usuario, y luego guarda el
     * nuevo registro.
     */
    @FXML
    fun handleGuardarButtonAction() {
        val empresa = empresaActualService.getEmpresa()
        if (empresa != null) {
            val tipoRegistro = tipoRegistroComboBox.selectionModel.selectedItem
            val concepto = conceptoField.text
            val cantidad = BigDecimal(cantidadField.text)

            // Crea un nuevo registro financiero basado en el tipo seleccionado
            val registroFinanciero = when (tipoRegistro) {
                "Inmovilizado intangible" -> InmovilizadoIntangible(empresa, concepto, cantidad)
                "Inmovilizado material" -> InmovilizadoMaterial(empresa, concepto, cantidad)
                "Inmovilizado financiero" -> InmovilizadoFinanciero(empresa, concepto, cantidad)
                "Amortizaciones" -> Amortizaciones(empresa, concepto, cantidad)
                "Existencias" -> Existencias(empresa, concepto, cantidad)
                "Realizable" -> Realizable(empresa, concepto, cantidad)
                "Disponible o efectivo" -> Disponible(empresa, concepto, cantidad)
                "Capital" -> Capital(empresa, concepto, cantidad)
                "Reservas" -> Reservas(empresa, concepto, cantidad)
                "Resultado del ejercicio" -> ResultadoEjercicio(empresa, concepto, cantidad)
                "Subvenciones" -> Subvenciones(empresa, concepto, cantidad)
                "Pasivo No Corriente" -> PasivoNoCorriente(empresa, concepto, cantidad)
                "Pasivo Corriente" -> PasivoCorriente(empresa, concepto, cantidad)

                else -> null
            }

            // Guarda el registro financiero
            if (registroFinanciero != null) {
                try {
                    registroFinancieroService.guardar(registroFinanciero)
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
        }
    }
}