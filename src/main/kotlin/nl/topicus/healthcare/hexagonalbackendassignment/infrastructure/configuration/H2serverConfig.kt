package nl.topicus.healthcare.hexagonalbackendassignment.infrastructure.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class H2serverConfig {

    @Bean(initMethod = "start", destroyMethod = "stop")
    fun h2Server(): org.h2.tools.Server =
        org.h2.tools.Server.createTcpServer("-tcp", "-tcpAllowOthers", "-tcpPort", "9092")
}
