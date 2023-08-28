package nl.topicus.healthcare.hexagonalbackendassignment.domain.errors

class NotFoundException(override val message: String?) : PatientMeasurementDomainException(message)