package de.komoot.challenge.repository

import de.komoot.challenge.domain.SignUpUser
import org.springframework.stereotype.Repository

@Repository
class FakeRecentUserDatabaseRepository {
    private val storage = mutableMapOf<Long, SignUpUser>()

    fun findAllRecentlyJoined(limit: Int = 10): List<SignUpUser> {
        val allUsersCount = storage.values.size
        val databaseLimit = if (allUsersCount > limit) limit else allUsersCount
        return storage.values.sortedBy(SignUpUser::created_at)
            .subList(fromIndex = 0, toIndex = databaseLimit)
    }

    fun save(signUpUser: SignUpUser) {
        storage[signUpUser.id] = signUpUser
    }

}
