package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import org.springframework.stereotype.Component

@Component
class PatientRepositoryAdapter(
    private val patientRepository: PatientRepository
) {
    fun findAll(): List<Patient> = patientRepository.findAll().map { it.toPatient() }
}