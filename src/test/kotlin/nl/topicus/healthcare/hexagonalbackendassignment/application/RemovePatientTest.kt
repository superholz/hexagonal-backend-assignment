package nl.topicus.healthcare.hexagonalbackendassignment.application

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.matchers.collections.shouldContainInOrder
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.UUID

@ExtendWith(MockKExtension::class)
class RemovePatientTest(
    @MockK val repository: PatientRepository,
    @MockK val measurementRepository: MeasurementRepository
) {

    @InjectMockKs lateinit var sut: RemovePatient

    val fixture = kotlinFixture { }
    @Test
    fun `removal of a patient should also remove also the measurements of that patient`() {
        val measurements = fixture<List<Measurement>>() { repeatCount { 2 } }

        every { measurementRepository.deleteMany(any()) } returns Unit
        every { measurementRepository.findForPatient(any())} returns measurements
        every { repository.deleteOne(any()) } returns Unit

        sut.removePatient(fixture<RemovePatient.Input>())

        val slot = slot<List<UUID>>()

        verify(exactly = 1) { measurementRepository.deleteMany(capture(slot)) }

        val result = slot.captured

        result shouldContainInOrder measurements.map { it.id }

    }

}