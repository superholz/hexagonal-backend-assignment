package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class RemovePatient(
    private val repository: PatientRepository,
) {
    fun removePatient(input: Input) {
        repository.deleteOne(UUID.fromString(input.id))
    }

    data class Input(
        val id: String,
    )
}
