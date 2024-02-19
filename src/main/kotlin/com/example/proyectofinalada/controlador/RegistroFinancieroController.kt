package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.modelo.*
import com.example.proyectofinalada.servicio.EmpresaService
import com.example.proyectofinalada.servicio.RegistroFinancieroService
import javafx.collections.FXCollections
import javafx.fxml.FXML
import javafx.scene.control.ComboBox
import org.springframework.stereotype.Controller
import java.awt.TextField

@Controller
class RegistroFinancieroController(private val registroFinancieroService: RegistroFinancieroService,
                                   private val empresaService: EmpresaService) {

    private lateinit var empresa: Empresa

    fun setEmpresa(empresa: Empresa) {
        this.empresa = empresa
    }

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

    @FXML
    fun handleVerRegistrosButtonAction() {
        // Obtiene todos los registros financieros
        val registrosFinancieros = registroFinancieroService.encontrarTodo()

        // Muestra los registros financieros (aquí puedes implementar la lógica para mostrarlos en tu interfaz de usuario)
        registrosFinancieros.forEach { println(it) }
    }

    @FXML
    fun handleEliminarRegistroButtonAction() {
        // Aquí puedes implementar la lógica para eliminar un registro financiero
        // Necesitarás alguna forma de seleccionar qué registro financiero se debe eliminar
    }
}