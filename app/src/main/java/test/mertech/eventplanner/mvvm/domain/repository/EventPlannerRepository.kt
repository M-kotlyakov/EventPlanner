package test.mertech.eventplanner.mvvm.domain.repository

import androidx.lifecycle.LiveData
import retrofit2.Response
import test.mertech.eventplanner.mvvm.domain.entity.Event
import test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity.WeatherYandex

interface EventPlannerRepository {

    suspend fun getWeather(lat: String, lon: String): Response<WeatherYandex>

    suspend fun addEventItem(event: Event)

    suspend fun deleteEventItem(event: Event)

    suspend fun editEventItem(event: Event)

    suspend fun getEventItem(eventItemId: Int): Event

    fun getEventList(): LiveData<List<Event>>
}