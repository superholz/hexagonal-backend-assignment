package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository

import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.springframework.data.jdbc.core.JdbcAggregateTemplate
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component
import java.util.UUID
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository.Patient as PatientEntity


@Component
class PatientRepositoryAdapter(
    private val repository: PatientCrudRepository,
    // the CrudRepository interface can not be used for inserting a new entity coming with an id, therefore
    // the more low level JdbcAggregateTemplate is used for the insert.
    private val template: JdbcAggregateTemplate,

    ) : PatientRepository {

    override fun findAll(): List<Patient> = repository.findAll().map { it.toPatient() }
    override fun getOne(id: UUID): Patient =
        repository.findByIdOrNull(id)?.toPatient() ?: throw PatientRepositoryException(
            "Patient with id '$id' could not be found."
        ).also { println(it) }


    override fun addOne(patient: Patient) {
        template.insert(patient.toEntity())
    }

    override fun deleteOne(id: UUID) {
        repository.deleteById(id)
    }

    private fun Patient.toEntity() = PatientEntity(id = this.id, name = name)
}

class PatientRepositoryException(override val message: String?) : RuntimeException(message)