package de.komoot.challenge.util

import de.komoot.challenge.domain.SignUpUser
import java.time.Instant

object DomainObjectCreator {

    fun signUpUser(
        name: String = "Marty",
        id: Long = 1985,
        createdAt: Instant = Instant.EPOCH
    ) = SignUpUser(
        name = name,
        id = id,
        created_at = createdAt
    )
}
