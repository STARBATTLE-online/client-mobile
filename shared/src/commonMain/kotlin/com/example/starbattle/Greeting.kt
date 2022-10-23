package com.example.starbattle

class Greeting {
    private val platform: Platform = getPlatform()

    fun greeting(): String {
        return "Guess what it is! > ${platform.name.reversed()}!" +
                "\nThere are only ${daysUntilNewYear()} days left until New Year! 🎅🏼 "
    }

    fun someText(text: String): String {
        return text + " from " + platform.name
    }
}