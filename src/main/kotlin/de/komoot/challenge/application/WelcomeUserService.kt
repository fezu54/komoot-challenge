package de.komoot.challenge.application

import de.komoot.challenge.domain.SignUpUser
import de.komoot.challenge.domain.WelcomeMessage
import de.komoot.challenge.repository.FakeRecentUserDatabaseRepository
import de.komoot.challenge.repository.SignUpRepository
import de.komoot.challenge.repository.mapper.toEntity
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import java.util.logging.Logger

@Service
class WelcomeUserService(
    @Value("\${server.mail}") private val sender: String,
    private val fakeRecentUserDatabaseRepository: FakeRecentUserDatabaseRepository,
    private val signUpRepository: SignUpRepository
) {
    private val logger = Logger.getLogger(WelcomeUserService::class.java.name)

    fun notifyAboutOtherUsers(signUpUser: SignUpUser) {
        fakeRecentUserDatabaseRepository.save(signUpUser)
        val recentUsers = fakeRecentUserDatabaseRepository.findAllRecentlyJoined()
        val welcomeMessage = WelcomeMessage(signUpUser, recentUsers)
        signUpRepository.postWelcomeMessage(welcomeMessage.toEntity(sender)).subscribe({
            logger.info { "Publish welcome message to user with ID '${signUpUser.id}'" }
        }) {
            logger.severe { "Failed to publish welcome message to user with ID '${signUpUser.id}'" }
        }
    }
}
