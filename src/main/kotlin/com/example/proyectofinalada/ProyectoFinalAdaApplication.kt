package com.example.proyectofinalada

import com.example.proyectofinalada.util.Navigator
import javafx.application.Application
import javafx.stage.Stage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class ProyectoFinalAdaApplication : Application() {

    private lateinit var context: ConfigurableApplicationContext

    override fun init() {
        context = SpringApplication.run(ProyectoFinalAdaApplication::class.java)
    }

    override fun start(primaryStage: Stage) {
        Navigator.setPrimaryStage(primaryStage)
        Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
    }

    override fun stop() {
        context.close()
    }
}

fun main(args: Array<String>) {
    Application.launch(ProyectoFinalAdaApplication::class.java, *args)
}