package me.prashant.testdrivenshop.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import me.prashant.testdrivenshop.data.local.room.AppDatabase
import me.prashant.testdrivenshop.data.local.room.CartDao
import me.prashant.testdrivenshop.data.mapper.CartItemDomainToEntityMapper
import me.prashant.testdrivenshop.data.mapper.CartItemEntityToDomainMapper
import me.prashant.testdrivenshop.data.repo.CartRepositoryImpl
import me.prashant.testdrivenshop.domain.repo.CartRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object LocalDataModule {
    @Provides
    @Singleton
    fun provideAppDatabase(
        @ApplicationContext appContext: Context,
    ): AppDatabase =
        Room
            .databaseBuilder(
                appContext,
                AppDatabase::class.java,
                "your_database_name",
            ).fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideCartDao(appDatabase: AppDatabase): CartDao = appDatabase.cartDao()

    @Provides
    @Singleton
    fun provideCartRepository(
        dao: CartDao,
        cartItemDomainToEntityMapper: CartItemDomainToEntityMapper,
        cartItemEntityToDomainMapper: CartItemEntityToDomainMapper,
    ): CartRepository =
        CartRepositoryImpl(
            dao,
            cartItemDomainToEntityMapper,
            cartItemEntityToDomainMapper,
        )
}
