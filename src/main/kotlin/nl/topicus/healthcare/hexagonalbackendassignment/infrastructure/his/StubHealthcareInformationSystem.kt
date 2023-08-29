package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.his

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

private val LOGGER: Logger = LoggerFactory.getLogger(StubHealthcareInformationSystem::class.java)

@Component
class StubHealthcareInformationSystem{

    fun shareMeasurement(measurement: HisMeasurement) {
        LOGGER.info("Measurement shared: $measurement")
    }
}