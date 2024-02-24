package com.example.proyectofinalada.servicio.empresa

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.repositorio.EmpresaRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service


/**
 * Servicio para interactuar con las empresas en la base de datos.
 *
 * Este servicio proporciona métodos para encontrar todas las empresas y guardar una nueva empresa.
 *
 * @property empresaRepository El repositorio para interactuar con las empresas en la base de datos.
 */
@Service
class EmpresaService(private val empresaRepository: EmpresaRepository) {

    /**
     * Encuentra todas las empresas en la base de datos.
     *
     * @return Una lista de todas las empresas.
     */
    @Transactional()
    fun encontrarTodo(): List<Empresa> {
        return try {
            empresaRepository.findAll()
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }

    /**
     * Guarda una nueva empresa en la base de datos.
     *
     * @param empresa La empresa a guardar.
     * @return La empresa guardada.
     */
    @Transactional
    fun guardar(empresa: Empresa): Empresa {
        return try {
            empresaRepository.save(empresa)
        } catch (e: Exception) {
            e.printStackTrace()
            empresa
        }
    }
}

