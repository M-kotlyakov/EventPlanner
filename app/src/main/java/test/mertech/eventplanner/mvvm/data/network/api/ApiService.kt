package test.mertech.eventplanner.mvvm.data.network.api

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query
import test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity.WeatherYandex

interface ApiService {

    @Headers("X-Yandex-API-Key: bfabb6c9-9e0c-4b98-9a61-4769be7b552f")
    @GET("v2/forecast?lat=56.0184&lon=92.8672&limit=7&lang=ru_RU&extra=true")
    suspend fun getWeather(
        @Query("lat") lat: String,
        @Query("lon") lon: String
    ): Response<WeatherYandex>
}