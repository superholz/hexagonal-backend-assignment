package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import org.springframework.data.repository.CrudRepository

interface PatientRepository : CrudRepository<PatientData, Long> {
}