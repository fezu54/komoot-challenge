package de.komoot.challenge.web.controller

import de.komoot.challenge.repository.WelcomeMessageEntity
import org.springframework.context.annotation.Profile
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/api")
@Profile("dev")
class FakePushNotificationController {

    @PostMapping("receive")
    fun receive(@RequestBody welcomeMessageEntity: WelcomeMessageEntity): ResponseEntity<WelcomeMessageEntity> {
        return ResponseEntity.ok(welcomeMessageEntity)
    }
}
