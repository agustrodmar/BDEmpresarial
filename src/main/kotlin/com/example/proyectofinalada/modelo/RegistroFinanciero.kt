@file:Suppress("SpellCheckingInspection")

package com.example.proyectofinalada.modelo

import jakarta.persistence.*
import org.springframework.data.annotation.Id

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_registro")
sealed class RegistroFinanciero(
    @jakarta.persistence.Id @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    val empresa: Empresa,

    val concepto: String
)

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_activo")
open class Activo(
    empresa: Empresa,
    concepto: String
) : RegistroFinanciero(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("activo_corriente")
class ActivoCorriente(
    empresa: Empresa,
    concepto: String
) : Activo(empresa = empresa, concepto = concepto)

// ACTIVO NO CORRIENTE
@Entity
@DiscriminatorValue("inmovilizado_intangible")
class InmovilizadoIntangible(empresa: Empresa, concepto: String) : Activo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("inmovilizado_material")
class InmovilizadoMaterial(empresa: Empresa, concepto: String) : Activo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("inmovilizado_financiero")
class InmovilizadoFinanciero(empresa: Empresa, concepto: String) : Activo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("amortizaciones")
class Amortizaciones(empresa: Empresa, concepto: String) : Activo(empresa = empresa, concepto = concepto)

// ACTIVO CORRIENTE
@Entity
@DiscriminatorValue("existencias")
class Existencias(empresa: Empresa, concepto: String) : Activo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("realizable")
class Realizable(empresa: Empresa, concepto: String) : Activo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("disponible")
class Disponible(empresa: Empresa, concepto: String) : Activo(empresa = empresa, concepto = concepto)

// PASIVO
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pasivo")
open class Pasivo(
    empresa: Empresa,
    concepto: String
) : RegistroFinanciero(empresa = empresa, concepto = concepto)

// NETO
@Entity
@DiscriminatorValue("capital")
class Capital(empresa: Empresa, concepto: String) : Pasivo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("reservas")
class Reservas(empresa: Empresa, concepto: String) : Pasivo(empresa = empresa, concepto = concepto)

@Suppress("SpellCheckingInspection")
@Entity
@DiscriminatorValue("resultado_ejercicio")
class ResultadoEjercicio(empresa: Empresa, concepto: String) : Pasivo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("subvenciones")
class Subvenciones(empresa: Empresa, concepto: String) : Pasivo(empresa = empresa, concepto = concepto)


// PASIVO NO CORRIENTE
@Entity
@DiscriminatorValue("deudas_largo_plazo")
class DeudasLargoPlazo(empresa: Empresa, concepto: String) : Pasivo(empresa = empresa, concepto = concepto)


// PASIVO CORRIENTE

@Entity
@DiscriminatorValue("pasivo_corriente")
class PasivoCorriente(
    empresa: Empresa,
    concepto: String
) : Pasivo(empresa = empresa, concepto = concepto)

@Entity
@DiscriminatorValue("deudas_corto_plazo")
class DeudasCortoPlazo(empresa: Empresa, concepto: String) : Pasivo(empresa = empresa, concepto = concepto)
