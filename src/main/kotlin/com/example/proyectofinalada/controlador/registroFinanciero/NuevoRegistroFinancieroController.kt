package com.example.proyectofinalada.controlador.registroFinanciero

import com.example.proyectofinalada.modelo.*
import com.example.proyectofinalada.servicio.EmpresaService
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import javafx.scene.control.TextField
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller

@Controller
class NuevoRegistroFinancieroController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @Autowired
    private lateinit var empresaService: EmpresaService

    @FXML
    private lateinit var empresa: Empresa

    @FXML
    private lateinit var tipoRegistroComboBox: ComboBox<String>

    @FXML
    private lateinit var conceptoField: TextField

    fun initialize() {
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


    @FXML
    fun handleGuardarButtonAction() {
        val tipoRegistro = tipoRegistroComboBox.selectionModel.selectedItem
        val concepto = conceptoField.text

        // Crea un nuevo registro financiero basado en el tipo seleccionado
        val registroFinanciero = when (tipoRegistro) {
            "Inmovilizado intangible" -> InmovilizadoIntangible(empresa, concepto)
            "Inmovilizado material" -> InmovilizadoMaterial(empresa, concepto)
            "Inmovilizado financiero" -> InmovilizadoFinanciero(empresa, concepto)
            "Amortizaciones" -> Amortizaciones(empresa, concepto)
            "Existencias" -> Existencias(empresa, concepto)
            "Realizable" -> Realizable(empresa, concepto)
            "Disponible o efectivo" -> Disponible(empresa, concepto)
            "Capital" -> Capital(empresa, concepto)
            "Reservas" -> Reservas(empresa, concepto)
            "Resultado del ejercicio" -> ResultadoEjercicio(empresa, concepto)
            "Subveciones" -> Subvenciones(empresa, concepto)
            "Pasivo No Corriente" -> PasivoNoCorriente(empresa, concepto)
            "Pasivo Corriente" -> PasivoCorriente(empresa, concepto)

            else -> null
        }

        // Guarda el registro financiero
        if (registroFinanciero != null) {
            registroFinancieroService.guardar(registroFinanciero)
        }
    }
}