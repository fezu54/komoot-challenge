package de.komoot.challenge.domain

import java.time.Instant

data class SignUpUser(
    val name: String,
    val id: Long,
    val created_at: Instant
)
