package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import org.springframework.data.repository.CrudRepository
import java.util.UUID

interface MeasurementCrudRepository : CrudRepository<Measurement, UUID>{

    fun findAllByPatientId(patientId: UUID): List<Measurement>
}
