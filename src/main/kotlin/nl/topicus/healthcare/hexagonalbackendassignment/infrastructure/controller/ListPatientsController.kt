package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import nl.topicus.healthcare.hexagonalbackendassignment.application.ListAllPatients
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController

@RestController
class ListPatientsController(
    private val service: ListAllPatients,
) {

    @GetMapping("/patients")
    fun retrievePatients(): ListOfPatientsResponse = service.listAllPatients().toOutput()

    data class ListOfPatientsResponse(
        val items: List<Patient>,
    )

    data class Patient(
        val id: String,
        val name: String,
    )

    private fun List<nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient>.toOutput() =
        ListOfPatientsResponse(
            items = this.map { Patient(it.id.toString(), it.name) },
        )
}
