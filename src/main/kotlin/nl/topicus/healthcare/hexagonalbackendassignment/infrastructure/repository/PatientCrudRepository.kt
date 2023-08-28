package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface PatientCrudRepository : CrudRepository<Patient, UUID>
