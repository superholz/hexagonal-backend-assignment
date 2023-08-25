package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.his

import java.time.Instant

data class HisMeasurement(
    val type: MeasurementType,
    val value: String,
    val unit: MeasurementUnit,
    val measureTime: Instant,
    val comment: String
)

enum class MeasurementType {
    BLOOD_SUGAR,
    HEARTH_FREQUENCY,
    BODY_WEIGHT
}

enum class MeasurementUnit {
    BEATS_PER_MINUTE,
    MMOL,
    KG
}