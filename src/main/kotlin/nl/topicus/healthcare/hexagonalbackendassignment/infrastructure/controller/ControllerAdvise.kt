package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementUnit
import nl.topicus.healthcare.hexagonalbackendassignment.domain.errors.InternalErrorException
import nl.topicus.healthcare.hexagonalbackendassignment.domain.errors.WrongStateDomainException
import nl.topicus.healthcare.hexagonalbackendassignment.domain.errors.WrongInputException
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository.MeasurementRepositoryException
import nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.repository.PatientRepositoryException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import java.lang.IllegalArgumentException
import java.lang.RuntimeException


@RestControllerAdvice(basePackages = ["nl.topicus.healthcare.hexagonalbackendassignment"])
class ControllerAdvise {

    @ExceptionHandler(value = [
        IllegalArgumentException::class,
        InternalErrorException::class,
        WrongStateDomainException::class,
        WrongInputException::class,
        WrongInputException::class,
        MeasurementUnit.Companion.InconsistentUnitException::class,
        MeasurementUnit.Companion.InconsistentUnitException::class,
        PatientRepositoryException::class,
        MeasurementRepositoryException::class,
    ])
    fun handleIllegalArgumentException(exception: RuntimeException): ResponseEntity<ProblemDetail> =
        createResponseEntity(exception, HttpStatus.BAD_REQUEST)

    @ExceptionHandler(value = [Throwable::class])
    fun handleNonCaughtException(exception: Throwable) =
        ResponseEntity(
            ProblemDetail(
                status = 500,
                title = "Unknown error",
                detail = "An unknown error occured."
            ),
            HttpStatus.INTERNAL_SERVER_ERROR
        )


}

data class ProblemDetail(val status: Int, val title: String, val detail: String)

private fun createProblemDetail(exception: Exception, status: HttpStatus) =
    ProblemDetail(
        status = status.value(),
        title = exception::class.simpleName ?: "no Title",
        detail = exception.message ?: "No Detail",
    )

private fun createResponseEntity(exception: Exception, status: HttpStatus) = ResponseEntity(
    createProblemDetail(exception, status), status
)
