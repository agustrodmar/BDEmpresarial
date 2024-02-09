package com.example.proyectofinalada

import javafx.fxml.FXMLLoader
import javafx.scene.Parent
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

val fxmlLoader = FXMLLoader(javaClass.getResource("/vista/MenuBienvenida.fxml"))
val root = fxmlLoader.load<Parent>()

@SpringBootApplication
class ProyectoFinalAdaApplication

fun main(args: Array<String>) {
    runApplication<ProyectoFinalAdaApplication>(*args)
}
