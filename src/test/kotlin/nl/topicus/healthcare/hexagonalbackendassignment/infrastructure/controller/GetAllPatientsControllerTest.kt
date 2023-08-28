package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import com.appmattus.kotlinfixture.kotlinFixture
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import nl.topicus.healthcare.hexagonalbackendassignment.application.ListAllPatients
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Import
import org.springframework.test.context.junit.jupiter.SpringExtension

@ExtendWith(SpringExtension::class)
@Import(ListPatientsController::class)
class GetAllPatientsControllerTest(
    @Autowired private val controller: ListPatientsController,
) {
    @MockkBean
    lateinit var service: ListAllPatients

    private val fixture = kotlinFixture {}

    @Test
    fun `should call application layer for retrieving Patients`() {
        every { service.listAllPatients() } returns fixture<List<Patient>>() { repeatCount { 3 } }

        controller.retrievePatients()

        verify(exactly = 1) { service.listAllPatients() }
    }
}
