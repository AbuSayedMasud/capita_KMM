package com.leads.capita

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.native.NativeSqliteDriver

// Actual implementation of the DatabaseDriverFactory for native platform
actual class DatabaseDriverFactory {
    // Function to create a SqlDriver instance
    actual fun createDriver(): SqlDriver {
        // Creating and returning a NativeSqliteDriver instance with the CapitaDb schema and database name
        return NativeSqliteDriver(CapitaDb.Schema, "test.db")
    }
}