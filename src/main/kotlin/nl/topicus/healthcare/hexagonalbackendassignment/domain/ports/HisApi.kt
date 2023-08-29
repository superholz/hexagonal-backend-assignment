package nl.topicus.healthcare.hexagonalbackendassignment.domain.ports

import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementForHis

interface HisApi {

    fun shareMeasurement(measurement: MeasurementForHis)
}