package com.example.proyectofinalada.repositorio

import com.example.proyectofinalada.modelo.Empresa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * El interface de Empresa
 */
@Repository
interface EmpresaRepository : JpaRepository<Empresa, Long>