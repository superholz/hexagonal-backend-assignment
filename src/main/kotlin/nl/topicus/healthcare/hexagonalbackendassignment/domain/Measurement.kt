package nl.topicus.healthcare.hexagonalbackendassignment.domain

import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementType.Companion.hasCorrectUnit
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.errors.WrongInputException
import java.math.BigDecimal
import java.time.Instant
import java.util.UUID

data class Measurement (
    val id: UUID,
    val patient: Patient,
    val type: MeasurementType,
    val value: String,
    val unit: MeasurementUnit,
    val measureTime: Instant,
    val status: MeasurementStatus?,
) {
    companion object {
        fun createMeasurement(id: UUID,
                              patient: Patient,
                              type: String,
                              value: String,
                              unit: String,
                              measureTime: String,
                              status: MeasurementStatus? = null

        ): Measurement {

            val numberValue = BigDecimal(value)
            val typeEnum = MeasurementType.fromString(type)
            val unitEnum = MeasurementUnit.fromString(unit)

            require(MeasurementType.inRange(numberValue, typeEnum)) {
                "Measurement value '$numberValue' is not in range for '${typeEnum.name}'"
            }
            require(typeEnum.hasCorrectUnit(unitEnum)) {
                "Measurement for '${typeEnum.name}' has to be '${typeEnum.unit}' but was '${unitEnum.name}'"
            }

            return Measurement(
                id = id,
                patient = patient,
                type = typeEnum,
                value = value,
                unit = unitEnum,
                measureTime = Instant.parse(measureTime),
                status = status,
            )
        }


    }
}


enum class MeasurementUnit {
    BEATS_PER_MINUTE,
    MMOL,
    KG;

    companion object {
        fun fromString(unit: String) =
            MeasurementUnit.values().find { it.name == unit } ?: throw InconsistentUnitException(
                "Measurement type can not be found for string '$unit'."
            ).also { println(it) }

        class InconsistentUnitException(override val message: String?) : WrongInputException(message)
    }
}

enum class MeasurementType(val minValue: BigDecimal, val maxValue: BigDecimal, val unit: MeasurementUnit) {
    BLOOD_SUGAR(BigDecimal("0.0"), BigDecimal("300.00"), MeasurementUnit.MMOL),
    HEARTH_FREQUENCY(BigDecimal("55.0"), BigDecimal("200.00"), MeasurementUnit.BEATS_PER_MINUTE),
    BODY_WEIGHT(BigDecimal("2.0"), BigDecimal("150.00"), MeasurementUnit.KG);

    companion object {
        fun fromString(type: String) =
            MeasurementType.values().find { it.name == type } ?: throw InconsistentMeasurementTypeException(
                "Measurement type can not be found for string '$type'."
            ).also { println(it) }

        fun inRange(value: BigDecimal, type: MeasurementType):Boolean =
            value >= type.minValue && value <= type.maxValue

        fun MeasurementType.hasCorrectUnit(unit: MeasurementUnit) = this.unit == unit

        class InconsistentMeasurementTypeException(override val message: String?) : WrongInputException(message)
    }
}

enum class MeasurementStatus {
    SHARED;
    companion object {
        fun fromString(status: String) =
            MeasurementStatus.values().find { it.name == status } ?: throw InconsistentStatusException(
                "Measurement status can not be found for string '$status'."
            ).also { println(it) }

        class InconsistentStatusException(override val message: String?) : WrongInputException(message)
    }

}