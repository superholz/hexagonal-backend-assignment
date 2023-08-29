package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.errors

open class WrongInputException(override val message: String?): Exception(message)