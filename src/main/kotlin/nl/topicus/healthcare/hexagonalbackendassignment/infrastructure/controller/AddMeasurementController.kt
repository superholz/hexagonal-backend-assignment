package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.application.AddMeasurement
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class AddMeasurementController(
    private val service: AddMeasurement,
) {

    @PostMapping("/measurements")
    fun addMeasurement(
        @RequestBody request: AddMeasurementRequest,
    ): ResponseEntity<Unit> {
        service.addMeasurement(request.createInput())
        return ResponseEntity(HttpStatus.CREATED)
    }


    data class AddMeasurementRequest(
        val patientId: String,
        val type: String,
        val value: String,
        val unit: String,
        val measureTime: String,
        val comment: String?
    )

    private fun AddMeasurementRequest.createInput() =
        AddMeasurement.Input(
            id = UUID.randomUUID(),
            patientId = patientId,
            type = type,
            value = value,
            unit = unit,
            measureTime = measureTime,
    )
}
