package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import org.springframework.data.annotation.Id
import java.util.UUID

data class Patient(
    @Id
    val id: UUID,
    val name: String,
) {
    fun toPatient() = Patient(id, name)

}
