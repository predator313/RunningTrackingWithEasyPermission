package com.example.runningtrackingapp.di

import android.content.Context
import androidx.room.Room
import com.example.runningtrackingapp.utils.RUNNING_DATABASE_NAME
import com.example.runningtrackingapp.db.RunDAO
import com.example.runningtrackingapp.db.RunningDataBase
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
    fun provideRunningDatabase(@ApplicationContext context: Context):RunningDataBase{
       return Room.databaseBuilder(
            context,
            RunningDataBase::class.java,
            RUNNING_DATABASE_NAME
        )
            .build()
    }
    @Provides
    @Singleton
    fun provideRunningDao(dataBase: RunningDataBase):RunDAO{
       return dataBase.getRunDao()

    }
}