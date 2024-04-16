package com.leads.capita

import app.cash.sqldelight.db.SqlDriver

// Declaration of an expect class DatabaseDriverFactory
expect class DatabaseDriverFactory {
    // Abstract function to create a SqlDriver
    fun createDriver(): SqlDriver
}