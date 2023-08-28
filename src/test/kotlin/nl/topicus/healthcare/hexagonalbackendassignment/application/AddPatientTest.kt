package nl.topicus.healthcare.hexagonalbackendassignment.application

import com.appmattus.kotlinfixture.kotlinFixture
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.verify
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MockKExtension::class)
class AddPatientTest(
    @MockK private val repository: PatientRepository,
) {
    @InjectMockKs
    lateinit var sut: AddPatient

    val fixture = kotlinFixture { }

    @Test
    fun `should call repository to add new patient`() {
        val input = fixture<AddPatient.Input>()

        every { repository.addOne(any()) } returns Unit

        sut.addPatient(input)

        verify(exactly = 1) { repository.addOne(any()) }
    }
}
