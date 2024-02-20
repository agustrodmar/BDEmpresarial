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
    private lateinit var nombreField: TextField


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