package com.example.proyectofinalada.servicio

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.repositorio.EmpresaRepository
import com.example.proyectofinalada.repositorio.RegistroFinancieroRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

/**
 * Aquí la lógica de negocio de Registro Financiero
 */
@Service
class RegistroFinancieroService(
    private val registroFinancieroRepository: RegistroFinancieroRepository,
    private val empresaRepository: EmpresaRepository
) {

    @Transactional()
    fun encontrarTodo(): List<RegistroFinanciero> {
        return registroFinancieroRepository.findAll()
    }

    @Transactional
    fun guardar(empresa: Empresa): Empresa {
        val savedEmpresa = empresaRepository.save(empresa)
        println("Empresa guardada: $savedEmpresa")
        return savedEmpresa
    }

}