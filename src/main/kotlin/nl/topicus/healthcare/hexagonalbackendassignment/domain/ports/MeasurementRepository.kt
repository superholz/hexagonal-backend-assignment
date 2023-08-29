package nl.topicus.healthcare.hexagonalbackendassignment.domain.ports

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import java.util.UUID

interface MeasurementRepository {
    fun findForPatient(patientId: UUID): List<Measurement>
    fun addOne(measurement: Measurement)

    fun deleteOne(measurementId: UUID)
}