package com.example.starbattle

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform