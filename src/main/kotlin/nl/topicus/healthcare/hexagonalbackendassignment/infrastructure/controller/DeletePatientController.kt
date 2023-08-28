package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.application.RemovePatient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
class DeletePatientController(
    private val service: RemovePatient,
) {

    @DeleteMapping("/patients/{id}")
    fun removePatient(
        @PathVariable id: String,
    ): ResponseEntity<Unit> {
        service.removePatient(RemovePatient.Input(id = id))
        return ResponseEntity(HttpStatus.NO_CONTENT)
    }
}
