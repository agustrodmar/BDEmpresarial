package com.example.proyectofinalada.servicio.empresa

import com.example.proyectofinalada.modelo.Empresa
import org.springframework.stereotype.Service

/**
 * El servicio de empresa que se usa para mantener
 * la empresa seleccionada a lo largo de la vida
 * util de la aplicación.
 */
@Service
class EmpresaActualService {
    private var empresa: Empresa? = null

    fun getEmpresa(): Empresa? {
        return empresa
    }

    fun setEmpresa(empresa: Empresa) {
        this.empresa = empresa
    }
}
