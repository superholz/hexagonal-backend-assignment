package nl.topicus.healthcare.hexagonalbackendassignment.domain

import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.errors.WrongInputException
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
                              status: MeasurementStatus?

        ): Measurement {
            return Measurement(
                id = id,
                patient = patient,
                type = MeasurementType.fromString(type),
                value = value,
                unit = MeasurementUnit.fromString(unit),
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

enum class MeasurementType {
    BLOOD_SUGAR,
    HEARTH_FREQUENCY,
    BODY_WEIGHT;

    companion object {
        fun fromString(type: String) =
            MeasurementType.values().find { it.name == type } ?: throw InconsistentMeasurementTypeException(
                "Measurement type can not be found for string '$type'."
            ).also { println(it) }

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