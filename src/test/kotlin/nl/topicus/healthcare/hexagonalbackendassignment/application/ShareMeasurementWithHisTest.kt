package nl.topicus.healthcare.hexagonalbackendassignment.application

import com.appmattus.kotlinfixture.kotlinFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.HisApi
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ShareMeasurementWithHisTest(
    @MockK private val hisApi: HisApi,
    @MockK private val repository: MeasurementRepository
){

    val fixture = kotlinFixture{}

    @InjectMockKs lateinit var sut: ShareMeasurementWithHis

    val measurement = fixture<Measurement>()
    val input = fixture< ShareMeasurementWithHis.Input>()

    @BeforeEach
    fun setup() {
        every { hisApi.shareMeasurement(any()) } returns Unit
        every { repository.getOne(any()) } returns measurement
        every { repository.saveSharingLogLine(any())} returns Unit
    }

    @Test
    fun `should call hisApi to share measurement`(){

        sut.shareMeasurement(input)

        verify(exactly = 1) { hisApi.shareMeasurement(any())}

    }

    @Test
    fun `should call repository to save sharing information when sharing a measurement`() {
        sut.shareMeasurement(input)

        verify(exactly = 1) { repository.saveSharingLogLine(any())}

    }

}