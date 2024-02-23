@file:Suppress("SpellCheckingInspection")

package com.example.proyectofinalada.modelo

import jakarta.persistence.*
import org.springframework.data.annotation.Id
import java.math.BigDecimal

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_registro")
sealed class RegistroFinanciero(
    @jakarta.persistence.Id @Id @GeneratedValue(strategy = GenerationType.AUTO)
    val id: Long = 0,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "empresa_id")
    val empresa: Empresa,

    val concepto: String,

    val cantidad: BigDecimal
)

// ACTIVO
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_activo")
open class Activo(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal
) : RegistroFinanciero(empresa = empresa, concepto = concepto, cantidad = cantidad)

// ACTIVO NO CORRIENTE
@Entity
@DiscriminatorValue("activo_no_corriente")
open class ActivoNoCorriente(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal
) : Activo(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("inmovilizado_intangible")
class InmovilizadoIntangible(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : ActivoNoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("inmovilizado_material")
class InmovilizadoMaterial(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : ActivoNoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("inmovilizado_financiero")
class InmovilizadoFinanciero(
    empresa: Empresa,
    concepto: String,
    cantidad : BigDecimal) : ActivoNoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("amortizaciones")
class Amortizaciones(
    empresa: Empresa,
    concepto: String,
    cantidad : BigDecimal) : ActivoNoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

// ACTIVO CORRIENTE
@Entity
@DiscriminatorValue("activo_corriente")
open class ActivoCorriente(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal
) : Activo(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("existencias")
class Existencias(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : ActivoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("realizable")
class Realizable(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : ActivoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("disponible")
class Disponible(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : ActivoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

// PASIVO
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "tipo_pasivo")
open class Pasivo(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal
) : RegistroFinanciero(empresa = empresa, concepto = concepto, cantidad = cantidad)

// NETO
@Entity
@DiscriminatorValue("neto")
open class Neto(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal
) : Pasivo(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("capital")
class Capital(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : Neto(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("reservas")
class Reservas(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : Neto(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("resultado_ejercicio")
class ResultadoEjercicio(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : Neto(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("subvenciones")
class Subvenciones(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : Neto(empresa = empresa, concepto = concepto, cantidad = cantidad)

// PASIVO NO CORRIENTE
@Entity
@DiscriminatorValue("pasivo_no_corriente")
open class PasivoNoCorriente(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal
) : Pasivo(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("deudas_largo_plazo")
class DeudasLargoPlazo(empresa: Empresa,
                       concepto: String,
                       cantidad: BigDecimal) : PasivoNoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)

// PASIVO CORRIENTE
@Entity
@DiscriminatorValue("pasivo_corriente")
open class PasivoCorriente(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal
) : Pasivo(empresa = empresa, concepto = concepto, cantidad = cantidad)

@Entity
@DiscriminatorValue("deudas_corto_plazo")
class DeudasCortoPlazo(
    empresa: Empresa,
    concepto: String,
    cantidad: BigDecimal) : PasivoCorriente(empresa = empresa, concepto = concepto, cantidad = cantidad)
