package com.example.proyectofinalada.modelo

import jakarta.persistence.*
import org.springframework.data.annotation.Id


@Entity
data class Empresa(
    @jakarta.persistence.Id @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    val nombre: String,

    @OneToMany(mappedBy = "empresa", cascade = [CascadeType.ALL], orphanRemoval = true)
    val registros: List<RegistroFinanciero> = ArrayList()
) {

}