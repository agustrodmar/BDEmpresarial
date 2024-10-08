package com.example.proyectofinalada.repositorio

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.modelo.RegistroFinanciero
import org.springframework.data.jpa.repository.JpaRepository

/**
 * El interface de RegistroFinanciero
 */
interface RegistroFinancieroRepository : JpaRepository<RegistroFinanciero, Long> {

    fun findByEmpresa(empresa: Empresa): List<RegistroFinanciero>
}