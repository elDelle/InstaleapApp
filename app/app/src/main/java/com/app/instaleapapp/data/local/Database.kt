package com.app.instaleapapp.data.local

import androidx.room.Database
import androidx.room.RoomDatabase

private const val DATABASE_VERSION = 1

@Database(
    version = DATABASE_VERSION
)
abstract class Database : RoomDatabase()