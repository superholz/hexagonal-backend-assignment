package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import org.springframework.data.annotation.Id
import org.springframework.data.relational.core.mapping.Table
import java.time.Instant
import java.util.UUID

@Table(name = "measurement_share_log")
data class MeasurementLogLine(
    @Id
    val id: UUID,
    val measurementId: UUID,
    val shareComment: String,
    val measurement: String,
    val timeOfSharing: Instant,
)
