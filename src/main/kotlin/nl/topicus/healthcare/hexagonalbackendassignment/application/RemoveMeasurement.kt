package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RemoveMeasurement(
    private val repository: MeasurementRepository,
) {
    fun removeMeasurement(input: Input) {
        repository.deleteOne(input.id)
    }

    data class Input(
        val id: UUID,
    )
}
