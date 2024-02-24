package com.example.proyectofinalada

import com.example.proyectofinalada.util.Navigator
import javafx.application.Application
import javafx.stage.Stage
import org.springframework.boot.SpringApplication
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.context.ConfigurableApplicationContext

/**
 * Clase principal de la aplicación.
 *
 * Esta clase es la entrada a la aplicación y se encarga de iniciar la aplicación Spring, cargar la escena inicial y cerrar el contexto de la aplicación cuando se detiene.
 */
@SpringBootApplication
class ProyectoFinalAdaApplication : Application() {

    // El contexto de la aplicación Spring.
    private lateinit var context: ConfigurableApplicationContext

    /**
     * Inicializa la aplicación.
     *
     * Este método se llama antes de que se inicie la aplicación JavaFX. Aquí es donde se inicia la aplicación Spring.
     */
    override fun init() {
        context = SpringApplication.run(ProyectoFinalAdaApplication::class.java)
    }

    /**
     * Inicia la aplicación.
     *
     * Este método se llama después de que se ha inicializado la aplicación JavaFX. Aquí es donde se carga la escena inicial en el escenario principal.
     *
     * @param primaryStage El escenario principal de la aplicación.
     */
    override fun start(primaryStage: Stage) {
        Navigator.setPrimaryStage(primaryStage)
        Navigator.loadScene("/vista/MenuBienvenida.fxml", context)
    }

    /**
     * Detiene la aplicación.
     *
     * Este método se llama cuando se detiene la aplicación JavaFX. Aquí es donde se cierra el contexto de la aplicación Spring.
     */
    override fun stop() {
        context.close()
    }
}

/**
 * Método principal de la aplicación.
 *
 * Este método lanza la aplicación JavaFX.
 *
 * @param args Los argumentos de la línea de comandos.
 */
fun main(args: Array<String>) {
    Application.launch(ProyectoFinalAdaApplication::class.java, *args)
}