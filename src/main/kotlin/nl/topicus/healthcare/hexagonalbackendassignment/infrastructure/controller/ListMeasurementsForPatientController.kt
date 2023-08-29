package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.application.ListMeasurementsOfPatient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.time.Instant
import java.util.UUID

@RestController
class ListMeasurementsForPatientController(
    private val service: ListMeasurementsOfPatient,
) {
    @GetMapping("/patients/{patientId}/measurements")
    fun retrieveMeasurementsForPatient(
        @PathVariable patientId: String
    ): ListOfMeasurementsResponse {
        val measurements = service.fetchAllMeasurementsForPatient(
            ListMeasurementsOfPatient.Input(UUID.fromString(patientId))
        )
        return ListOfMeasurementsResponse(
            items = measurements.toResponse()
        )
    }

    data class ListOfMeasurementsResponse(
        val items: List<Measurement>,
    )

    data class Measurement(
        val id: String,
        val patientName: String,
        val type: String,
        val value: String,
        val unit: String,
        val measureTime: Instant,
        val status: String?
    )

    private fun ListMeasurementsOfPatient.Output.toResponse() =
        this.measurements.map {
            Measurement(
                id = it.id.toString(),
                patientName = it.patient.name,
                type = it.type.name,
                value = it.value,
                unit = it.unit.name,
                measureTime = it.measureTime,
                status = it.status?.name
            )
        }
}
