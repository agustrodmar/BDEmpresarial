package com.example.proyectofinalada

import com.example.proyectofinalada.controlador.EmpresaController
import com.example.proyectofinalada.controlador.MenuBienvenidaController
import javafx.application.Application
import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import javafx.scene.Scene
import javafx.stage.Stage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

@SpringBootApplication
class ProyectoFinalAdaApplication : Application() {

    private lateinit var context: ConfigurableApplicationContext
    private lateinit var primaryStage: Stage

    override fun init() {
        context = SpringApplication.run(ProyectoFinalAdaApplication::class.java)
    }

    override fun start(primaryStage: Stage) {
        this.primaryStage = primaryStage // Guarda la referencia a primaryStage
        showMenuBienvenida()
    }

    fun showMenuBienvenida() {
        val fxmlLoader = FXMLLoader(javaClass.getResource("/vista/MenuBienvenida.fxml"))
        fxmlLoader.setControllerFactory { context.getBean(it) }
        val root = fxmlLoader.load<Parent>()
        primaryStage.scene = Scene(root)
        primaryStage.show()
    }

    override fun stop() {
        context.close()
    }
}

fun main(args: Array<String>) {
    Application.launch(ProyectoFinalAdaApplication::class.java, *args)
}