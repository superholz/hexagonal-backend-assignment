package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import org.springframework.data.annotation.Id
import java.time.Instant
import java.util.UUID

data class Measurement(
    @Id
    val id: UUID,
    val patientId: UUID,
    val type: String,
    val value: String,
    val unit: String,
    val measureTime: Instant,
    val status: String?
)