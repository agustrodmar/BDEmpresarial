package com.example.proyectofinalada.servicio.empresa

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.repositorio.EmpresaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


/**
 * Aquí la lógica de negocio de Empresa
 */
@Service
class EmpresaService(private val empresaRepository: EmpresaRepository) {

    @Transactional()
    fun encontrarTodo(): List<Empresa> {
        return empresaRepository.findAll()
    }

    @Transactional
    fun guardar(empresa: Empresa): Empresa {
        return empresaRepository.save(empresa)
    }

}