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
class AddPatientIT {

    @LocalServerPort
    var port: Int = 8080

    @Test
    fun `post measurement should succeed`() {
        Given {
            basePath("/patients")
            contentType(ContentType.JSON)
            body(
                """
                |{
                |    "name": "Harry"
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
