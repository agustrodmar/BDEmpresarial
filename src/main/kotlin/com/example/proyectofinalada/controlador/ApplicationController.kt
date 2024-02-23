package com.example.proyectofinalada.controlador

import javafx.event.ActionEvent
import javafx.fxml.FXML
import javafx.scene.control.Button
import javafx.scene.control.TextField
import org.springframework.context.ConfigurableApplicationContext
import org.springframework.core.env.ConfigurableEnvironment
import org.springframework.core.env.MapPropertySource
import org.springframework.stereotype.Controller


/**
 * Esta clase se encarga de controlar la vista
 * de ApplicationSettings. Su función principal
 * es modificar el Application properties de la GUI
 */
@Controller
class ApplicationController(private val context: ConfigurableApplicationContext) {

    @FXML
    private lateinit var urlField: TextField

    @FXML
    private lateinit var usernameField: TextField

    @FXML
    private lateinit var passwordField: TextField

    @FXML
    private lateinit var portField: TextField


    @FXML
    private fun saveSettings() {
        val map = mapOf(
            "spring.datasource.url" to urlField.text,
            "spring.datasource.username" to usernameField.text,
            "spring.datasource.password" to passwordField.text,
            "server.port" to portField.text
        )

        val env = context.environment as ConfigurableEnvironment
        env.propertySources.addFirst(MapPropertySource("custom", map))
    }
}