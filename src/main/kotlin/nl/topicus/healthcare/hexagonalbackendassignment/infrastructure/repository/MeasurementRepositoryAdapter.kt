package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import com.fasterxml.jackson.databind.ObjectMapper
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementForHis
import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementStatus
import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementType
import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementUnit
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.stereotype.Component
import java.util.UUID
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository.Measurement as MeasurementEntity

@Component
class MeasurementRepositoryAdapter(
    private val repository: MeasurementCrudRepository,
    private val patientRepository: PatientRepository,
    private val template: JdbcAggregateTemplate,
    private val mapper: ObjectMapper,

    ) : MeasurementRepository {
    override fun findForPatient(patientId: UUID): List<Measurement> {
        val patient = patientRepository.getOne(patientId)
        return repository.findAllByPatientId(patientId).map {
            it.toMeasurement(patient)
        }
    }

    override fun addOne(measurement: Measurement) {
        template.insert(measurement.toMeasurementEntity())
    }

    override fun persist(measurement: Measurement) {
        repository.save(measurement.toMeasurementEntity())
    }

    override fun getOne(measurementId: UUID): Measurement {
        val measurementEntity = repository.findById(measurementId).get()
        val patient = patientRepository.getOne(measurementEntity.patientId)

        return measurementEntity.toMeasurement(patient)
    }

    override fun deleteOne(measurementId: UUID) {
        repository.deleteById(measurementId)
    }

    override fun deleteMany(ids: List<UUID>){
        repository.deleteAllById(ids)
    }

    override fun saveSharingLogLine(measurement: MeasurementForHis) {
        template.insert(measurement.toLogline())
    }

    private fun MeasurementEntity.toMeasurement(patient: Patient) =
        Measurement(
            id = id,
            patient = patient,
            type = MeasurementType.fromString(type),
            value = value,
            unit = MeasurementUnit.fromString(unit),
            status =  if (status != null) MeasurementStatus.fromString(status) else null,
            measureTime = measureTime
        )

    private fun Measurement.toMeasurementEntity() =
        MeasurementEntity(
            id = id,
            patientId = patient.id,
            type = type.name,
            value = value,
            unit = unit.name,
            measureTime = measureTime,
            status = status?.name
        )

    private fun MeasurementForHis.toLogline() =
        MeasurementLogLine(
            id = UUID.randomUUID(),
            measurementId = measurement.id,
            shareComment = comment,
            timeOfSharing = timeOfSharing,
            measurement = mapper.writeValueAsString(measurement)
        )

}

