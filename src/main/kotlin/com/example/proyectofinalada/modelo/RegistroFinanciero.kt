package com.example.proyectofinalada.modelo

import jakarta.persistence.*
import org.springframework.data.annotation.Id

@Entity
data class RegistroFinanciero(
    @jakarta.persistence.Id @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    val empresa: Empresa,

    val concepto: String,

    val tipo: Tipo,

    val clasificacion: Clasificacion
) {
    enum class Tipo {
        ACTIVO, PASIVO
    }

    enum class Clasificacion {
        CORRIENTE, NO_CORRIENTE
    }
}