package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.his

import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementForHis
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.HisApi
import org.springframework.stereotype.Component

@Component
class HisAdapter(
    private val his: StubHealthcareInformationSystem
) : HisApi {
    override fun shareMeasurement(measurement: MeasurementForHis) {
        his.shareMeasurement(measurement.toHisMeasurement())
    }

    private fun MeasurementForHis.toHisMeasurement(): HisMeasurement {

        require(measurement.type.name in MeasurementType.values().map { it.toString() }) {
            "Measurement type ${measurement.type.name} not found as type for His Api."
        }

        require(measurement.unit.name in MeasurementUnit.values().map { it.toString() }) {
            "Measurement unit ${measurement.unit.name} not found as unit for His Api."
        }

        return HisMeasurement(
            type = MeasurementType.values().first { measurement.type.name == it.name },
            unit = MeasurementUnit.values().first { measurement.unit.name == it.name },
            value = measurement.value,
            measureTime = measurement.measureTime,
            comment = comment
        )
    }
}