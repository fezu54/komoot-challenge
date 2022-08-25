package de.komoot.challenge.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Repository
import org.springframework.web.reactive.function.client.WebClient
import org.springframework.web.util.UriComponentsBuilder
import reactor.core.publisher.Mono

@Repository
class SignUpRepository(
    private val webClient: WebClient,
    @Value("\${remote.endpoint.push-notification-service}") private val pushNotificationEndpoint: String
) {
    fun postWelcomeMessage(welcomeMessageEntity: WelcomeMessageEntity): Mono<ResponseEntity<Void>> {
        val uriBuilder = UriComponentsBuilder.fromUriString(pushNotificationEndpoint)
        return webClient.post()
            .uri(uriBuilder.toUriString())
            .bodyValue(welcomeMessageEntity)
            .retrieve()
            .toBodilessEntity()
    }
}
