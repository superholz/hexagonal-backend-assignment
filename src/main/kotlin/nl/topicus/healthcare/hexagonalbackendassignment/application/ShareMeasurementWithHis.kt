package nl.topicus.healthcare.hexagonalbackendassignment.application

import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementForHis
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.HisApi
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import org.springframework.stereotype.Service
import java.time.Instant
import java.util.UUID

@Service
class ShareMeasurementWithHis(
    private val hisApi: HisApi,
    private val repository: MeasurementRepository,
) {
    fun shareMeasurement(input: Input) {
        val measurement = repository.getOne(input.id)

        val measurementToShare = MeasurementForHis(
            measurement = measurement,
            comment = input.comment,
            timeOfSharing = Instant.now()
        )

        hisApi.shareMeasurement(measurementToShare)
        repository.saveSharingLogLine(measurementToShare)

    }

    data class Input(
        val id: UUID,
        val comment: String,
    )
}
