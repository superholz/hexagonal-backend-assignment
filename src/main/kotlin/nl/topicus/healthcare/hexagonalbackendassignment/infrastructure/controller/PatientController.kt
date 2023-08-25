package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.controller

import org.springframework.web.bind.annotation.*
import kotlin.random.Random.Default.nextLong

@RestController
@RequestMapping("/patients")
class PatientController {

    @GetMapping("")
    fun getTaak(): List<PatientDTO> {
        // TODO: Call PatientDomain service and return patients from the store.
        return listOf(PatientDTO(nextLong(), "Test patient"))
    }

}