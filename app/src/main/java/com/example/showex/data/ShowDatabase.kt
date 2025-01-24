package com.example.showex.data

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase

@Database(
    entities = [ShowEntity::class],
    version = 2,
    exportSchema = true
)
@TypeConverters(Converters::class)
abstract class ShowDatabase : RoomDatabase() {
    abstract fun showDao(): ShowDao

    companion object {
        val MIGRATION_1_2 = object : Migration(1, 2) {
            override fun migrate(database: SupportSQLiteDatabase) {
                database.execSQL(
                    "ALTER TABLE shows ADD COLUMN currentSeason INTEGER NOT NULL DEFAULT 1"
                )
                database.execSQL(
                    "ALTER TABLE shows ADD COLUMN totalSeasons INTEGER DEFAULT NULL"
                )
                
                database.execSQL("""
                    CREATE TABLE shows_new (
                        id TEXT NOT NULL PRIMARY KEY,
                        title TEXT NOT NULL,
                        status TEXT NOT NULL,
                        currentEpisode INTEGER NOT NULL,
                        totalEpisodes INTEGER,
                        currentSeason INTEGER NOT NULL,
                        totalSeasons INTEGER,
                        rating REAL NOT NULL,
                        categories TEXT NOT NULL,
                        notes TEXT NOT NULL
                    )
                """)

                database.execSQL("""
                    INSERT INTO shows_new (
                        id, title, status, currentEpisode, totalEpisodes,
                        currentSeason, totalSeasons, rating, categories, notes
                    )
                    SELECT 
                        id, title, status, currentEpisode, totalEpisodes,
                        1, NULL, rating, categories, notes
                    FROM shows
                """)

                database.execSQL("DROP TABLE shows")

                database.execSQL("ALTER TABLE shows_new RENAME TO shows")
            }
        }
    }
} 