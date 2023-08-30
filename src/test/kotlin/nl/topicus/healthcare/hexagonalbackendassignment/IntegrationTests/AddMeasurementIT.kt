package nl.topicus.healthcare.hexagonalbackendassignment.IntegrationTests

import io.restassured.http.ContentType
import io.restassured.module.kotlin.extensions.Given
import io.restassured.module.kotlin.extensions.Then
import io.restassured.module.kotlin.extensions.When
import org.apache.http.HttpStatus
import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort


@SpringBootTest(
    webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
    properties = ["server.port=8080"]
)
class AddMeasurementIT {

    @LocalServerPort
    var port: Int = 8080

    @Test
    fun `should succeed to add a measurement`() {

        Given {
            basePath("/measurements")
            contentType(ContentType.JSON)
            body(
                """
                |{
                |"patient_id": "5cbda1e6-f582-41b2-a78b-88327d4db5c1",
                |"type": "BLOOD_SUGAR",
                |"value": "30.0",
                |"unit": "MMOL",
                |"measure_time": "2023-08-19T18:30:00Z"
                |}
                """.trimMargin()
            )
        } When {
            post()
        } Then {
            statusCode(HttpStatus.SC_CREATED)
        }
    }

}
