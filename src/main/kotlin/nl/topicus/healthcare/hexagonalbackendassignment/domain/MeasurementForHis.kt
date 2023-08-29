package nl.topicus.healthcare.hexagonalbackendassignment.domain

import java.time.Instant

data class MeasurementForHis(
    val measurement: Measurement,
    val comment: String,
    val timeOfSharing: Instant
)
