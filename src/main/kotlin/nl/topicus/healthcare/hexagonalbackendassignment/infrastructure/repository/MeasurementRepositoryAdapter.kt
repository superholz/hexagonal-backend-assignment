package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
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
    private val template: JdbcAggregateTemplate

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

    override fun deleteOne(measurementId: UUID) {
        repository.deleteById(measurementId)
    }

    private fun MeasurementEntity.toMeasurement(patient: Patient) =
        Measurement(
            id = id,
            patient = patient,
            type = MeasurementType.fromString(type),
            value = value,
            unit = MeasurementUnit.fromString(unit),
            comment = comment,
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
            comment = comment
        )
}

