package com.taufik.weatherx.di

import android.content.Context
import androidx.room.Room
import com.taufik.weatherx.data.local.db.WeatherXDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(
        @ApplicationContext mContext: Context
    ) = Room.databaseBuilder(
        context = mContext,
        klass = WeatherXDatabase::class.java,
        name = "WeatherX.db"
    ).fallbackToDestructiveMigration().build()

    @Singleton
    @Provides
    fun provideDao(database: WeatherXDatabase) = database.getWeatherXDao()
}