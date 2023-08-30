package nl.topicus.healthcare.hexagonalbackendassignment.domain.errors

open class WrongInputException(override val message: String?): Exception(message)