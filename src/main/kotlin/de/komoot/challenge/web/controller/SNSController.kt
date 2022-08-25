package de.komoot.challenge.web.controller

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import de.komoot.challenge.application.WelcomeUserService
import de.komoot.challenge.domain.SignUpUser
import de.komoot.challenge.repository.SNSSubscriptionRepository
import de.komoot.challenge.web.api.SubscriptionDTO
import kotlinx.coroutines.runBlocking
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
class SNSController(
    private val objectMapper: ObjectMapper,
    private val welcomeUserService: WelcomeUserService,
    private val snsSubscriptionRepository: SNSSubscriptionRepository
) {

    @PostMapping("/notifications")
    fun notifications(@RequestBody rawMessage: String): ResponseEntity<Nothing> {
        //TODO why does AWS sends the json as plain/text content type?
        val subscriptionDTO = objectMapper.readValue(rawMessage, object : TypeReference<SubscriptionDTO>() {})
        if (subscriptionDTO.type == "SubscriptionConfirmation") {
            runBlocking { snsSubscriptionRepository.confirmSubscription(subscriptionDTO.token) }
        } else if(subscriptionDTO.type == "Notification") {
            val signUpUser = objectMapper.readValue(subscriptionDTO.message, object : TypeReference<SignUpUser>() {})
            welcomeUserService.notifyAboutOtherUsers(signUpUser)
        }
        return ResponseEntity.ok().build()
    }
}
