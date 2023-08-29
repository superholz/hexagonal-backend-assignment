package nl.topicus.healthcare.hexagonalbackendassignment.domain.errors

open class WrongStateDomainException(
    override val message: String?,
): RuntimeException(message)


