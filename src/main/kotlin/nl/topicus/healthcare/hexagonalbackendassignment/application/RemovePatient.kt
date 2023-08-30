package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RemovePatient(
    private val repository: PatientRepository,
    private val measurementRepository: MeasurementRepository,
) {
    fun removePatient(input: Input) {

        val measurementsOfPatient = measurementRepository.findForPatient(input.id)

        measurementRepository.deleteMany(measurementsOfPatient.map { it.id })
        repository.deleteOne(input.id)
    }

    data class Input(
        val id: UUID,
    )
}
