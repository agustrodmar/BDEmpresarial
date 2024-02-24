package com.example.proyectofinalada.util

import javafx.util.Callback
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

/**
 * Fábrica de controladores para Spring.
 *
 * Esta clase es un componente de Spring que se utiliza para crear instancias de controladores.
 * Implementa la interfaz `Callback` de JavaFX, lo que permite a Spring inyectar dependencias en los controladores.
 *
 * @property context El contexto de la aplicación de Spring.
 */
@Component
class SpringControllerFactory(private val context: ApplicationContext) : Callback<Class<*>, Any> {

    /**
     * Crea una instancia de un controlador.
     *
     * Este método se llama cuando se necesita una nueva instancia de un controlador.
     * Utiliza el contexto de la aplicación de Spring para crear la instancia, lo que permite la inyección de dependencias.
     *
     * @param param La clase del controlador.
     * @return Una nueva instancia del controlador.
     */
    override fun call(param: Class<*>): Any {
        return context.getBean(param)
    }
}
