package ru.tech.feature_main_screen.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.tech.feature_main_screen.data.remote.EcommerceApi
import ru.tech.feature_main_screen.data.repository.EcommerceRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideRepository(
        api: EcommerceApi
    ) = EcommerceRepository(api)

    @Singleton
    @Provides
    fun providePokeApi(): EcommerceApi {
        return Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://shopapi-0575.restdb.io/rest/")
            .build()
            .create(EcommerceApi::class.java)
    }
}