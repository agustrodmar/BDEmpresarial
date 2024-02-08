package com.example.proyectofinalada.repositorio

import com.example.proyectofinalada.modelo.RegistroFinanciero
import org.springframework.data.jpa.repository.JpaRepository

interface RegistroFinancieroRepository : JpaRepository<RegistroFinanciero, Long> {
}