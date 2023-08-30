package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.application.AddPatient
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class AddPatientController(
    private val service: AddPatient,
) {

    @PostMapping("/patients")
    fun addPatient(
        @RequestBody request: AddPatientRequest,
    ): ResponseEntity<Unit> {
        service.addPatient(request.createPatient())
        return ResponseEntity(HttpStatus.CREATED)
    }

    private fun AddPatientRequest.createPatient() = AddPatient.Input(
        id = UUID.randomUUID(),
        name = name,
    )

    data class AddPatientRequest(
        val name: String,
    )
}
