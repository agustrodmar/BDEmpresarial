package com.example.proyectofinalada.repositorio

import com.example.proyectofinalada.modelo.Empresa
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface EmpresaRepository : JpaRepository<Empresa, Long> {
}