package nl.topicus.healthcare.hexagonalbackendassignment.domain.ports

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementForHis
import java.util.UUID

interface MeasurementRepository {
    fun findForPatient(patientId: UUID): List<Measurement>
    fun addOne(measurement: Measurement)

    fun persist(measurement: Measurement)

    fun getOne(measurementId: UUID): Measurement
    fun deleteOne(measurementId: UUID)
    fun deleteMany(ids: List<UUID>)
    fun saveSharingLogLine(measurement: MeasurementForHis)

}
