package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.his

import org.slf4j.Logger
import org.slf4j.LoggerFactory

private val LOGGER: Logger = LoggerFactory.getLogger(StubHealthcareInformationSystem::class.java)

class StubHealthcareInformationSystem {

    fun shareMeasurement(measurement: HisMeasurement) {
        LOGGER.info("Measurement shared: $measurement")
    }
}