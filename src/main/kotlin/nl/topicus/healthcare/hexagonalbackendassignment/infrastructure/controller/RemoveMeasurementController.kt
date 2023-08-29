package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.application.RemoveMeasurement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
class RemoveMeasurementController(
    private val service: RemoveMeasurement,
) {
    @DeleteMapping("/measurements/{id}")
    fun removeMeasurement(
        @PathVariable id: UUID,
    ): ResponseEntity<Unit> {
        service.removeMeasurement(RemoveMeasurement.Input(id = id))
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
