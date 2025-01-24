package com.example.showex.di

import android.content.Context
import androidx.room.Room
import com.example.showex.data.ShowDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideShowDatabase(
        @ApplicationContext context: Context
    ): ShowDatabase = Room.databaseBuilder(
        context,
        ShowDatabase::class.java,
        "shows_database"
    )
    .addMigrations(ShowDatabase.MIGRATION_1_2)
    .build()

    @Provides
    @Singleton
    fun provideShowDao(database: ShowDatabase) = database.showDao()
} 