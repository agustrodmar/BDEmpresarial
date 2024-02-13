package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.servicio.EmpresaService
import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.scene.control.TextField
import javafx.stage.Stage
import org.springframework.stereotype.Controller

@Controller
class NuevaBaseDeDatosController(private val empresaService: EmpresaService) {

    @FXML
    private lateinit var idField: TextField

    @FXML
    private lateinit var nombreField: TextField

    @FXML
    fun handleGuardarButtonAction() {
        val id = idField.text.toLong()
        val nombre = nombreField.text

        val nuevaEmpresa = Empresa(id = id, nombre = nombre)
        empresaService.guardar(nuevaEmpresa)
    }
}