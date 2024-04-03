package com.leads.capitabull

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform