package nl.topicus.healthcare.hexagonalbackendassignment.application

import com.appmattus.kotlinfixture.kotlinFixture
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.impl.annotations.InjectMockKs
import io.mockk.impl.annotations.MockK
import io.mockk.junit5.MockKExtension
import io.mockk.slot
import io.mockk.verify
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Measurement
import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementType
import nl.topicus.healthcare.hexagonalbackendassignment.domain.MeasurementUnit
import nl.topicus.healthcare.hexagonalbackendassignment.domain.Patient
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.MeasurementRepository
import nl.topicus.healthcare.hexagonalbackendassignment.domain.ports.PatientRepository
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.MethodSource
import java.time.Instant
import java.util.UUID
import java.util.stream.Stream

@ExtendWith(MockKExtension::class)
class AddMeasurementTest(
    @MockK private val repository: MeasurementRepository,
    @MockK private val patientRepository: PatientRepository
) {
    @InjectMockKs
    lateinit var sut: AddMeasurement

    val fixture = kotlinFixture { }

    @Test
    fun `should call repository to add new measurement with correctly mapped domain object`() {
        val timeString = "2023-08-29T12:34:56.789Z"

        val patient = fixture<Patient>()

        val timeAsInstant = Instant.parse(timeString)

        val input = fixture<AddMeasurement.Input>()
            .copy(
                unit = "MMOL",
                type = "BLOOD_SUGAR",
                measureTime = timeString
            )

        every { repository.addOne(any()) } returns Unit
        every { patientRepository.getOne(any()) } returns patient

        sut.addMeasurement(input)

        val slot = slot<Measurement>()

        verify(exactly = 1) { repository.addOne(capture(slot)) }

        val result = slot.captured

        result.id shouldBe input.id
        result.measureTime shouldBe timeAsInstant
        result.patient.id shouldBe patient.id
        result.patient.name shouldBe patient.name
        result.type shouldBe MeasurementType.BLOOD_SUGAR
        result.unit shouldBe MeasurementUnit.MMOL
        result.value shouldBe input.value

    }

    @ParameterizedTest
    @MethodSource("measurements")
    fun `should throw an exception when the value is not in Range`(
        type: String,
        value: String,
        unit: String,
    ) {

        shouldThrow<IllegalArgumentException> {
            Measurement.createMeasurement(
                id = UUID.randomUUID(),
                patient = fixture<Patient>(),
                type = type,
                value = value,
                unit = unit,
                measureTime = Instant.now().toString()
            )
        }
    }


    companion object {
        @JvmStatic
        fun measurements(): Stream<Arguments> = Stream.of(
            Arguments.of("BLOOD_SUGAR","-1","MMOL"),
            Arguments.of("BLOOD_SUGAR","301","MMOL"),
            Arguments.of("BLOOD_SUGAR","300.1","MMOL"),
            Arguments.of("HEARTH_FREQUENCY","54.9","BEATS_PER_MINUTE"),
            Arguments.of("HEARTH_FREQUENCY","201","BEATS_PER_MINUTE"),
            Arguments.of("HEARTH_FREQUENCY","200.1","BEATS_PER_MINUTE"),
            Arguments.of("BODY_WEIGHT","1.9","KG"),
            Arguments.of("BODY_WEIGHT","150.1","KG"),
        )

    }
}


