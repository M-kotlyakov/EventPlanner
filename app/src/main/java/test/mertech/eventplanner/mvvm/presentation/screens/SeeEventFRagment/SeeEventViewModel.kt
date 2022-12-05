package test.mertech.eventplanner.mvvm.presentation.screens.SeeEventFRagment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.mertech.eventplanner.mvvm.domain.UseCases.GetWeatherUseCase
import test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity.WeatherYandex
import javax.inject.Inject

class SeeEventViewModel@Inject constructor(): ViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherYandex>()
    val weatherResponse: LiveData<WeatherYandex>
        get() = _weatherResponse
}