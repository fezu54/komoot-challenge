package de.komoot.challenge.repository.mapper

import de.komoot.challenge.domain.WelcomeMessage
import de.komoot.challenge.repository.WelcomeMessageEntity

fun WelcomeMessage.toEntity(sender: String): WelcomeMessageEntity = WelcomeMessageEntity(
    sender = sender,
    receiver = signUpUserId,
    message = message,
    recent_user_ids = recentUserIds
)
