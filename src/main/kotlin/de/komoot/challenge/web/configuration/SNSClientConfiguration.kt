package de.komoot.challenge.web.configuration

import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import software.amazon.awssdk.regions.Region
import software.amazon.awssdk.services.sns.SnsClient

@Configuration
class SNSClientConfiguration(@Value("\${remote.endpoint.aws-region}") private val awsRegion: String) {
    @Bean
    fun snsClient(): SnsClient = SnsClient.builder()
        .region(Region.of(awsRegion))
        .build()
}
