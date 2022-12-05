package test.mertech.eventplanner.mvvm.domain.UseCases

import retrofit2.Response
import test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity.WeatherYandex
import test.mertech.eventplanner.mvvm.domain.repository.EventPlannerRepository
import javax.inject.Inject

class GetWeatherUseCase @Inject constructor(
    private val eventPlannerRepository: EventPlannerRepository
) {
    suspend fun getWeather(lat: String, lon: String): Response<WeatherYandex> = eventPlannerRepository.getWeather(lat, lon)
}