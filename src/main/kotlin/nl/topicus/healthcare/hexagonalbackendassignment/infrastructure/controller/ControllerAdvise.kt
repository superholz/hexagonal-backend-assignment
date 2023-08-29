package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementUnit
import nl.topicus.healthcare.hexagonalbackendassignment.domain.errors.InternalErrorException
import nl.topicus.healthcare.hexagonalbackendassignment.domain.errors.WrongStateDomainException
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.errors.WrongInputException
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository.MeasurementRepositoryException
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository.PatientRepositoryException
import org.springframework.http.HttpStatus
import org.springframework.http.ProblemDetail
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice


@RestControllerAdvice(basePackages = ["package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller"])
class ControllerAdvise {

    @ExceptionHandler(value = [WrongStateDomainException::class])
    fun handleDomainException(exception: WrongStateDomainException) =
        ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [WrongInputException::class])
    fun handleBadConversionException(exception: WrongInputException) =
        ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [MeasurementUnit.Companion.InconsistentUnitException::class])
    fun handleBadConversionException(exception: MeasurementUnit.Companion.InconsistentUnitException) =
        ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value =[InternalErrorException::class])
    fun handleInternalErrorException(exception: InternalErrorException) =
        ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)

    @ExceptionHandler(value = [PatientRepositoryException::class])
    fun handleBadInputException(exception: PatientRepositoryException) =
        ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [MeasurementRepositoryException::class])
    fun handleBadInputException(exception: MeasurementRepositoryException) =
        ProblemDetail.forStatus(HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [Throwable::class])
    fun handleNonCaughtException(exception: Throwable) = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR)



}
