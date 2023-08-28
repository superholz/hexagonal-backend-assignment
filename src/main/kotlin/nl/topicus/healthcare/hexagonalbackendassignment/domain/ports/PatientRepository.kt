package nl.topicus.healthcare.hexagonalbackendassignment.domain.ports

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import java.util.UUID

interface PatientRepository {
    fun findAll(): List<Patient>
    fun getOne(id: UUID): Patient
    fun addOne(patient: Patient)
    fun deleteOne(id: UUID)
}
