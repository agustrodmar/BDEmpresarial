package com.example.proyectofinalada.servicio

import com.example.proyectofinalada.modelo.Empresa
import com.example.proyectofinalada.modelo.RegistroFinanciero
import com.example.proyectofinalada.repositorio.EmpresaRepository
import com.example.proyectofinalada.repositorio.RegistroFinancieroRepository
import jakarta.transaction.Transactional
import org.springframework.stereotype.Service

/**
 * Servicio para interactuar con los registros financieros en la base de datos.
 *
 * Este servicio proporciona métodos para encontrar todos los registros financieros, guardar un nuevo registro financiero,
 * eliminar un registro financiero y encontrar registros financieros por empresa.
 *
 * @property registroFinancieroRepository El repositorio para interactuar con los registros financieros en la base de datos.
 * @property empresaRepository El repositorio para interactuar con las empresas en la base de datos.
 */
@Service
class RegistroFinancieroService(
    private val registroFinancieroRepository: RegistroFinancieroRepository,
    private val empresaRepository: EmpresaRepository
) {

    /**
     * Guarda un nuevo registro financiero en la base de datos.
     *
     * @param registroFinanciero El registro financiero a guardar.
     * @return El registro financiero guardado.
     */
    @Transactional
    fun guardar(registroFinanciero: RegistroFinanciero): RegistroFinanciero {
        return try {
            registroFinancieroRepository.save(registroFinanciero)
        } catch (e: Exception) {
            e.printStackTrace()
            registroFinanciero
        }
    }

    /**
     * Elimina un registro financiero de la base de datos.
     *
     * @param registroFinanciero El registro financiero a eliminar.
     */
    @Transactional
    fun eliminar(registroFinanciero: RegistroFinanciero) {
        try {
            registroFinancieroRepository.delete(registroFinanciero)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    /**
     * Encuentra los registros financieros de una empresa específica.
     *
     * @param empresa La empresa cuyos registros financieros se van a encontrar.
     * @return Una lista de los registros financieros de la empresa.
     */
    fun encontrarPorEmpresa(empresa: Empresa): List<RegistroFinanciero> {
        return try {
            registroFinancieroRepository.findByEmpresa(empresa)
        } catch (e: Exception) {
            e.printStackTrace()
            emptyList()
        }
    }
}