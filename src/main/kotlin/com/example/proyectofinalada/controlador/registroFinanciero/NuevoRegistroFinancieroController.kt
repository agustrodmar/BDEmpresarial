package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.*
import com.example.proyectofinalada.servicio.empresa.EmpresaService
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

    fun handleVolverAlMenuButtonAction() {
        Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
    }

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
                registroFinancieroService.guardar(registroFinanciero)
            }
        }
    }
}
