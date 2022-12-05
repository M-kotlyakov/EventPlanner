package test.mertech.eventplanner.mvvm.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import test.mertech.eventplanner.mvvm.data.database.AppDataBase
import test.mertech.eventplanner.mvvm.data.database.EventPlannerDao
import test.mertech.eventplanner.mvvm.data.network.api.ApiService
import test.mertech.eventplanner.mvvm.data.network.utils.Constants
import test.mertech.eventplanner.mvvm.data.repository.EventPlannerRepositoryImpl
import test.mertech.eventplanner.mvvm.domain.repository.EventPlannerRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindEventPlannerRepository(impl: EventPlannerRepositoryImpl): EventPlannerRepository

    companion object {

        @ApplicationScope
        fun okHttpClientBuilder(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(
                HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
            )
            .build()

        @ApplicationScope
        @Provides
        fun provideRetrofitInstance(
            BASE_URL: String
        ): ApiService =
            Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClientBuilder())
                .build()
                .create(ApiService::class.java)

        @ApplicationScope
        @Provides
        fun provideListDao(
            application: Application
        ): EventPlannerDao {
            return AppDataBase.getInstance(application).eventPlannerDao()
        }
    }
}