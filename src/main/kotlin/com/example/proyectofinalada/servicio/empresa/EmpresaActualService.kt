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

    /**
     * Obtiene la empresa actualmente seleccionada.
     *
     * @return La empresa actualmente seleccionada, o null si no hay ninguna empresa seleccionada.
     */
    fun getEmpresa(): Empresa? {
        return empresa
    }

    /**
     * Establece la empresa actualmente seleccionada.
     *
     * @param empresa La empresa a seleccionar.
     */
    fun setEmpresa(empresa: Empresa) {
        this.empresa = empresa
    }
}