package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class AddPatient(
    private val repository: PatientRepository,
) {

    fun addPatient(input: Input) {
        repository.addOne(input.toPatient())
    }

    data class Input(
        val id: String,
        val name: String,
    )

    private fun Input.toPatient() = Patient(id = UUID.fromString(id), name = name)
}
