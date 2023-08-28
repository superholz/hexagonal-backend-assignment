package nl.topicus.healthcare.hexagonalbackendassignment.application

import com.appmattus.kotlinfixture.kotlinFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class ListAllPatientsTest(
    @MockK private val repository: PatientRepository,
) {

    @InjectMockKs
    lateinit var sut: ListAllPatients

    val fixture = kotlinFixture()

    @Test
    fun `should call repository to retrieve patients`() {
        every { repository.findAll() } returns fixture<List<Patient>>() { repeatCount { 3 } }
        sut.listAllPatients()

        verify(exactly = 1) { repository.findAll() }
    }
}
