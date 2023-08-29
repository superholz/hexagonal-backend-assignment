package nl.topicus.healthcare.hexagonalbackendassignment.domain.errors

open class InternalErrorException(override val message: String?): RuntimeException(message)