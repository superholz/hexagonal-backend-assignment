package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.springframework.stereotype.Service

@Service
class ListAllPatients(
    private val repository: PatientRepository
) {

    fun listAllPatients(): List<Patient> {
        return repository.findAll()
    }
}