package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class ListMeasurementsOfPatient(
    private val repository: MeasurementRepository
) {
    fun fetchAllMeasurementsForPatient(input: Input): Output {
        val measurements = repository.findForPatient(input.patientId)
        return Output(measurements = measurements)
    }

    data class Input(
        val patientId: UUID
    )

    data class Output(
        val measurements: List<Measurement>
    )
}