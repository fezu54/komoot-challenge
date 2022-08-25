package de.komoot.challenge.repository

import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Repository
import software.amazon.awssdk.services.sns.SnsClient
import software.amazon.awssdk.services.sns.model.ConfirmSubscriptionResponse

@Repository
class SNSSubscriptionRepository(
    @Value("\${remote.endpoint.sns-topic}") private val topic: String,
    @Value("\${server.protocol}") private val protocol: String,
    @Value("\${server.host}") private val host: String,
    private val snsClient: SnsClient
) {
    init {
        snsClient.subscribe {
            it.protocol(this@SNSSubscriptionRepository.protocol)
            it.endpoint("$protocol://$host/api/notifications")
            it.topicArn(topic)
            it.returnSubscriptionArn(true)
        }
    }

    suspend fun confirmSubscription(token: String): ConfirmSubscriptionResponse = snsClient.confirmSubscription {
        it.topicArn(topic)
        it.token(token)
    }
}
