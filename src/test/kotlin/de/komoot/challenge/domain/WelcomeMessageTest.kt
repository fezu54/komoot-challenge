package de.komoot.challenge.domain

import de.komoot.challenge.util.DomainObjectCreator
import org.junit.jupiter.api.Test
import java.time.Instant
import kotlin.test.assertEquals
import kotlin.test.assertTrue

internal class WelcomeMessageTest {
    @Test
    fun `Should generate welcome message when no recent users found`() {
        // GIVEN
        val signUpUser = DomainObjectCreator.signUpUser()
        val recentUsers = listOf<SignUpUser>()
        // WHEN
        val welcomeMessage = WelcomeMessage(signUpUser, recentUsers)

        // THEN
        assertEquals(expected = "Hi Marty, welcome to komoot.", actual = welcomeMessage.message)
    }

    @Test
    fun `Should generate welcome message when one recent user was found`() {
        // GIVEN
        val signUpUser = DomainObjectCreator.signUpUser()
        val recentUsers =
            listOf(DomainObjectCreator.signUpUser("DocBrown", 1955, Instant.EPOCH.plusSeconds(100)))
        // WHEN
        val welcomeMessage = WelcomeMessage(signUpUser, recentUsers)

        // THEN
        assertEquals(
            expected = "Hi Marty, welcome to komoot. DocBrown also joined recently",
            actual = welcomeMessage.message
        )
    }

    @Test
    fun `Should generate welcome message when two recent users were found`() {
        // GIVEN
        val signUpUser = DomainObjectCreator.signUpUser()
        val recentUsers =
            listOf(
                DomainObjectCreator.signUpUser("DocBrown", 1955, Instant.EPOCH.plusSeconds(100)),
                DomainObjectCreator.signUpUser("Jennifer", 2015, createdAt = Instant.EPOCH.plusSeconds(200))
            )
        // WHEN
        val welcomeMessage = WelcomeMessage(signUpUser, recentUsers)

        // THEN
        assertEquals(
            expected = "Hi Marty, welcome to komoot. Jennifer and DocBrown also joined recently",
            actual = welcomeMessage.message
        )
    }

    @Test
    fun `Should generate welcome message when three recent users were found`() {
        // GIVEN
        val signUpUser = DomainObjectCreator.signUpUser()
        val recentUsers =
            listOf(
                DomainObjectCreator.signUpUser(
                    name = "DocBrown",
                    id = 1955,
                    createdAt = Instant.EPOCH.plusSeconds(100)
                ),
                DomainObjectCreator.signUpUser(
                    name = "Jennifer",
                    id = 2015,
                    createdAt = Instant.EPOCH.plusSeconds(200)
                ),
                DomainObjectCreator.signUpUser(name = "Biff", id = 1888, createdAt = Instant.EPOCH.plusSeconds(300))
            )

        // WHEN
        val welcomeMessage = WelcomeMessage(signUpUser, recentUsers)

        // THEN
        assertEquals(
            expected = "Hi Marty, welcome to komoot. Biff, Jennifer and DocBrown also joined recently",
            actual = welcomeMessage.message
        )
    }

    @Test
    fun `Should generate welcome message with randomized users when more than three recent users found`() {
        // GIVEN
        val signUpUser = DomainObjectCreator.signUpUser()
        val recentUsers =
            listOf(
                DomainObjectCreator.signUpUser(
                    name = "DocBrown",
                    id = 1955,
                    createdAt = Instant.EPOCH.plusSeconds(100)
                ),
                DomainObjectCreator.signUpUser(
                    name = "Jennifer",
                    id = 2015,
                    createdAt = Instant.EPOCH.plusSeconds(200)
                ),
                DomainObjectCreator.signUpUser(name = "Biff", id = 1888, createdAt = Instant.EPOCH.plusSeconds(300)),
                DomainObjectCreator.signUpUser(name = "Clara", id = 1887, createdAt = Instant.EPOCH.plusSeconds(400))
            )

        // WHEN
        val welcomeMessage = WelcomeMessage(signUpUser, recentUsers)

        // THEN
        val recentUserNames = welcomeMessage.message
            .replace("Hi Marty, welcome to komoot. ", "")
            .replace(",", "")
            .replace("and ", "")
            .replace("also joined recently", "")
            .split(" ").map { it.trim() }
        val names = recentUsers.map { it.name }
        assertTrue(names.contains(recentUserNames.component1()))
        assertTrue(names.contains(recentUserNames.component2()))
        assertTrue(names.contains(recentUserNames.component3()))
    }
}
