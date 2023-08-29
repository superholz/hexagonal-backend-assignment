package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AddMeasurement(
    private val repository: MeasurementRepository,
    private val patientRepository: PatientRepository,
) {
    fun addMeasurement(input: Input) {

        val patient = patientRepository.getOne(UUID.fromString(input.patientId))

        val measurement = Measurement.createMeasurement(
            id = input.id,
            patient = patient,
            type = input.type,
            value = input.value,
            unit = input.unit,
            measureTime = input.measureTime,
            comment = input.comment
        )

        repository.addOne(measurement)
    }

    data class Input(
        val id: UUID,
        val patientId: String,
        val type: String,
        val value: String,
        val unit: String,
        val measureTime: String,
        val comment: String?
    )


}
