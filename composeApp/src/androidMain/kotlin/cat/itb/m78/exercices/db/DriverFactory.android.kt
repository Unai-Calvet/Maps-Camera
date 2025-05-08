package cat.itb.m78.exercices.db

import app.cash.sqldelight.db.SqlDriver
import app.cash.sqldelight.driver.android.AndroidSqliteDriver

actual fun createDriver(): SqlDriver {
    val appContext = applicationContext
    return AndroidSqliteDriver(Database.Schema, appContext, "myDatabase.db")
}