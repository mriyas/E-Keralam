package com.infoapp.core.di

import com.google.firebase.database.FirebaseDatabase
import com.infoapp.data.repository.MenuRepositoryImpl
import com.infoapp.domain.repository.MenuRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object FirebaseModule {
    const val DATABASE_URL =
        "https://k-info-a6ff9-default-rtdb.asia-southeast1.firebasedatabase.app"

    @Provides
    @Singleton
    fun provideFirebaseDatabase(): FirebaseDatabase {
        return FirebaseDatabase.getInstance(
            DATABASE_URL
        )
    }
}

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMenuRepository(impl: MenuRepositoryImpl): MenuRepository
}
