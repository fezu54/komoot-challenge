package de.komoot.challenge.domain

class WelcomeMessage(private val signUpUser: SignUpUser, recentUsers: List<SignUpUser>) {
    var message: String
        private set
    private var likeMindedPeople: List<SignUpUser>
    var recentUserIds: List<Long>
        private set
    val signUpUserId = signUpUser.id

    init {
        message = "Hi ${signUpUser.name}, welcome to komoot."
        likeMindedPeople = recentUsers.filter { it.id != signUpUser.id }
        if (likeMindedPeople.size > 3) {
            likeMindedPeople = likeMindedPeople.shuffled().subList(fromIndex = 0, toIndex = 3)
        }
        recentUserIds = likeMindedPeople.map { it.id }
        message += additionalUsers(
            likeMindedPeople.getOrNull(0),
            likeMindedPeople.getOrNull(1),
            likeMindedPeople.getOrNull(2)
        )
    }

    private fun additionalUsers(user0: SignUpUser?, user1: SignUpUser?, user2: SignUpUser?): String {
        val part1 = user0?.let { " ${user0.name} also joined recently" } ?: ""
        val part2 = user1?.let { " ${user1.name} and" } ?: ""
        val part3 = user2?.let { " ${user2.name}," } ?: ""
        return part3 + part2 + part1
    }
}
