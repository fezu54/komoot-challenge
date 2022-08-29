package de.komoot.challenge.domain

import com.fasterxml.jackson.core.type.TypeReference
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.exc.InvalidFormatException
import org.junit.jupiter.api.Assertions.assertDoesNotThrow
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
internal class SignUpUserTest(@Autowired private val objectMapper: ObjectMapper) {

    @Test
    fun `Should deserialize 'created_at' property when time is zulu string `() {
        // GIVEN
        val zuluStringJson = """{
                                "name": "Marcus",
                                "id": 1589278470,
                                "created_at": "2020-05-12T16:11:54.000Z"
                                }"""
        // WHEN
        val signUpUser: () -> Unit = { objectMapper.readValue(zuluStringJson, object : TypeReference<SignUpUser>() {}) }
        // THEN
        assertDoesNotThrow(signUpUser)
    }

    @Test
    fun `Should deserialize 'created_at' property when time is offset string`() {
        // GIVEN
        val offsetStringJson = """{
                                "name": "Marcus",
                                "id": 1589278470,
                                "created_at": "2020-05-12T16:11:54.000+00:00"
                                }"""
        // WHEN
        val signUpUser: () -> Unit =
            { objectMapper.readValue(offsetStringJson, object : TypeReference<SignUpUser>() {}) }
        // THEN
        assertDoesNotThrow(signUpUser)
    }

    @Test
    fun `Should throw 'InvalidFormatException' when 'created_at' property is not a valid iso8601 string`() {
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
        assertThrows<InvalidFormatException>(signUpUser)
    }
}
