package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.configuration

import nl.topicus.healthcare.hexagonalbackendassignment.domain.PatientDomainService
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BeanConfiguration {

    @Bean
    fun getPatientDomainService(): PatientDomainService = PatientDomainService()
}