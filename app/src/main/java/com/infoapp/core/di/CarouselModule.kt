package com.infoapp.core.di


import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.infoapp.data.repository.CarouselRepositoryImpl
import com.infoapp.domain.repository.CarouselRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CarouselDataModule {

    @Provides
    @Singleton
    @Named("dashboardCarouselsRef")
    fun provideDashboardCarouselsRef(database: FirebaseDatabase): DatabaseReference =
        database.getReference("dashboard_carousels")
}

@Module
@InstallIn(SingletonComponent::class)
abstract class CarouselBindModule {

    @Binds
    @Singleton
    abstract fun bindCarouselRepository(
        impl: CarouselRepositoryImpl
    ): CarouselRepository
}