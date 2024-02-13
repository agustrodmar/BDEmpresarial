package com.example.proyectofinalada.util

import javafx.util.Callback
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component
class SpringControllerFactory(private val context: ApplicationContext) : Callback<Class<*>, Any> {
    override fun call(param: Class<*>): Any {
        return context.getBean(param)
    }
}