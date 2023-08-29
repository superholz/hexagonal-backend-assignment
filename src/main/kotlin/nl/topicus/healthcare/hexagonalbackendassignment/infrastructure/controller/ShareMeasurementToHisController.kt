package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.application.ShareMeasurementWithHis
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PatchMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
class ShareMeasurementToHisController(
    private val service: ShareMeasurementWithHis,
) {

    @PatchMapping("/measurements")
    fun shareMeasurement(
        @RequestBody request: ShareMeasurementRequest,
    ): ResponseEntity<Unit> {
        service.shareMeasurement(ShareMeasurementWithHis.Input(
            id = request.measurementId,
            comment = request.comment,
        ))
        return ResponseEntity(HttpStatus.CREATED)
    }

    data class ShareMeasurementRequest(
        val measurementId: UUID,
        val comment: String,
    )
}
