package nl.topicus.healthcare.hexagonalbackendassignment.domain.errors

open class PatientMeasurementDomainException(
    override val message: String?,
): RuntimeException(message)
