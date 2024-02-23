package com.example.proyectofinalada.controlador

import com.example.proyectofinalada.servicio.RegistroFinancieroService
import com.example.proyectofinalada.servicio.empresa.EmpresaActualService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Controller
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver
import java.awt.Desktop
import java.io.File
import java.io.PrintWriter

@Controller
class GeneradorHtmlThymeLeafController {

    @Autowired
    private lateinit var registroFinancieroService: RegistroFinancieroService

    @Autowired
    private lateinit var empresaActualService: EmpresaActualService

    fun generateHtmlView() {
        val templateResolver = ClassLoaderTemplateResolver()
        templateResolver.suffix = ".html"
        templateResolver.templateMode = TemplateMode.HTML

        val templateEngine = TemplateEngine()
        templateEngine.setTemplateResolver(templateResolver)

        val context = Context()
        val empresa = empresaActualService.getEmpresa()
        if (empresa != null) {
            val registrosFinancieros = registroFinancieroService.encontrarPorEmpresa(empresa)
            context.setVariable("registrosFinancieros", registrosFinancieros)
        }

        val html = templateEngine.process("registrosFinancieros", context)

        // Guarda la cadena HTML en un archivo
        val file = File("registrosFinancieros.html")
        PrintWriter(file).use { out -> out.println(html) }

        // Abre el archivo en el navegador predeterminado
        if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
            Desktop.getDesktop().browse(file.toURI())
        }
    }
}