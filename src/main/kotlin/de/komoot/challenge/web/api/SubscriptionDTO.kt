package de.komoot.challenge.web.api


import com.fasterxml.jackson.annotation.JsonProperty

data class SubscriptionDTO(
    @JsonProperty("Message") val message: String = "",
    @JsonProperty("MessageId") val messageId: String = "",
    @JsonProperty("Signature") val signature: String = "",
    @JsonProperty("SignatureVersion") val signatureVersion: String = "",
    @JsonProperty("SigningCertURL") val signingCertURL: String = "",
    @JsonProperty("SubscribeURL") val subscribeURL: String = "",
    @JsonProperty("Timestamp") val timestamp: String = "",
    @JsonProperty("Token") val token: String = "",
    @JsonProperty("TopicArn") val topicArn: String = "",
    @JsonProperty("Type") val type: String = ""
)
