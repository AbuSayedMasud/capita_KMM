package com.leads.capita

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform