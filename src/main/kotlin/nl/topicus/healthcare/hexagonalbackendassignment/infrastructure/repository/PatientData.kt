package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient

data class PatientData(
    val id: Long,
    val name: String
) {
    fun toPatient() = Patient(id, name)
}