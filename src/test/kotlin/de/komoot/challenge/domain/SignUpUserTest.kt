package de.komoot.challenge.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class SignUpUserTest(@Autowired private val objectMapper: ObjectMapper) {

    @Test
    fun `Should deserialize 'created_at' when value is LocalDateTime string with nano seconds`() {
        // GIVEN
        val invalidJsonString = """{
                                "name": "Marcus",
                                "id": 1589278470,
                                "created_at": "2020-05-12T16:11:54.000"
                                }"""
        // WHEN
        val signUpUser: () -> Unit =
            { objectMapper.readValue(invalidJsonString, object : TypeReference<SignUpUser>() {}) }

        // THEN
        assertDoesNotThrow(signUpUser)
    }

    @Test
    fun `Should deserialize 'created_at' when value is LocalDateTime string`() {
        // GIVEN
        val invalidJsonString = """{
                                "name": "Marcus",
                                "id": 1589278470,
                                "created_at": "2022-08-29T15:28:14"
                                }"""
        // WHEN
        val signUpUser: () -> Unit =
            { objectMapper.readValue(invalidJsonString, object : TypeReference<SignUpUser>() {}) }

        // THEN
        assertDoesNotThrow(signUpUser)
    }
}
