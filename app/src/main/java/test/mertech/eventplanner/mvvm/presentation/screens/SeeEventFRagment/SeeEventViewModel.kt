package test.mertech.eventplanner.mvvm.presentation.screens.SeeEventFRagment

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import test.mertech.eventplanner.mvvm.domain.UseCases.GetWeatherUseCase
import test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity.WeatherYandex
import javax.inject.Inject

class SeeEventViewModel@Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase
): ViewModel() {

    private val _weatherResponse = MutableLiveData<WeatherYandex>()
    val weatherResponse: LiveData<WeatherYandex>
        get() = _weatherResponse

//    fun getWeather() = viewModelScope.launch {
//
//        getWeatherUseCase.getWeather().let {  response ->
//
//            if(response.isSuccessful) {
//                Log.d("EventViewModel", "getWeather Response: ${response.message()}")
//                Log.d("EventViewModel", "getWeather Response: ${response.body()}")
//                _weatherResponse.postValue(response.body())
//            } else {
//                Log.d("EventViewModel", "getWeather ERROR Response: ${response.message()}")
//            }
//        }
//    }
}