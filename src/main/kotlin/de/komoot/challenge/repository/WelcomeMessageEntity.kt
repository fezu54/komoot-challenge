package de.komoot.challenge.repository

data class WelcomeMessageEntity(
    val sender: String,
    val receiver: Long,
    val message: String,
    val recent_user_ids: List<Long>
)
