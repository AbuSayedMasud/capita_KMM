package com.leads.capita

import android.content.Context
import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

// Actual implementation of the DatabaseDriverFactory for Android platform
actual class DatabaseDriverFactory(private val context: Context) {
    // Function to create a SqlDriver instance
    actual fun createDriver(): SqlDriver {
        // Creating and returning an AndroidSqliteDriver instance with the CapitaDb schema,
        // application context, and database name
        return AndroidSqliteDriver(CapitaDb.Schema, context, "test.db")
    }
}