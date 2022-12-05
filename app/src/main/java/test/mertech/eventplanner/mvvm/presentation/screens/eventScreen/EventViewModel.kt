package test.mertech.eventplanner.mvvm.presentation.screens.eventScreen

import android.annotation.SuppressLint
import android.app.Application
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.mertech.eventplanner.mvvm.domain.UseCases.AddEventUseCase
import test.mertech.eventplanner.mvvm.domain.UseCases.EditEventItemUseCase
import test.mertech.eventplanner.mvvm.domain.UseCases.GetWeatherUseCase
import test.mertech.eventplanner.mvvm.domain.entity.Event
import test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity.WeatherYandex
import test.mertech.eventplanner.mvvm.presentation.screens.eventScreen.EventFragment.Companion.BASE_ICON_URL
import test.mertech.eventplanner.mvvm.presentation.utils.geoLocation.GeoCoderHandler
import test.mertech.eventplanner.mvvm.presentation.utils.geoLocation.getAddressFromLocation
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit
import javax.inject.Inject

class EventViewModel @Inject constructor(
    private val getWeatherUseCase: GetWeatherUseCase,
    private val addEventUseCase: AddEventUseCase,
    private val editEventItemUseCase: EditEventItemUseCase
): ViewModel() {

    var diffBetweenDate = UNDEFINED_DIFF

    private val _weatherResponse = MutableLiveData<WeatherYandex>()
    val weatherResponse: LiveData<WeatherYandex>
        get() = _weatherResponse

    private val _weatherTemp = MutableLiveData<Double>()
    val weatherTemp: LiveData<Double>
        get() = _weatherTemp

    private val _weatherDescription = MutableLiveData<String>()
    val weatherDescription: LiveData<String>
        get() = _weatherDescription

    private val _icon = MutableLiveData<String>()
    val icon: LiveData<String>
        get() = _icon

    private val _errorInputTitle = MutableLiveData<Boolean>()
    val errorInputTitle: LiveData<Boolean>
        get() = _errorInputTitle

    private val _errorInputDate = MutableLiveData<Boolean>()
    val errorInputDate: LiveData<Boolean>
        get() = _errorInputDate

    private val _errorInputCity = MutableLiveData<Boolean>()
    val errorInputCity: LiveData<Boolean>
        get() = _errorInputCity

    private val _errorInputStreet = MutableLiveData<Boolean>()
    val errorInputStreet: LiveData<Boolean>
        get() = _errorInputStreet

    fun addEventItem(
        title: String,
        descr: String,
        date: String,
        city: String,
        street: String,
        house: String,
        status: String
    ) {
        viewModelScope.launch {
            val item = Event(
                title = title,
                description = descr,
                date = date,
                city = city,
                street = street,
                house = house,
                status = status,
                imageUrl = BASE_ICON_URL + _icon.value.toString() + ICON_EXTENSION,
                celsius = _weatherTemp.value.toString(),
                weather_description = weatherDescription.value.toString()
            )
            diffBetweenDate = differenceBetweenDate(date, changeDateFormat(CURRENT_BASE_DATE.toString()))
            if (diffBetweenDate > 7) {
                throw RuntimeException(
                    "Exceeding the limit of the forecast interval. " +
                        "The forecast is only available for 7 days."
                )
            } else {
                addEventUseCase.addEventItem(item)
            }
        }
}

    fun editEventItem(item: Event) {

        diffBetweenDate = differenceBetweenDate(item.date, changeDateFormat(CURRENT_BASE_DATE.toString()))
        if (diffBetweenDate > 7) {
            throw RuntimeException(
                "Exceeding the limit of the forecast interval. " +
                    "The forecast is only available for 7 days." +
                    " diffBetweenDate = $diffBetweenDate"
            )
        } else {
            val event = item.copy(
                celsius = _weatherTemp.value.toString(),
                weather_description = weatherDescription.value.toString(),
                imageUrl = BASE_ICON_URL + _icon.value.toString() + ICON_EXTENSION
            )
            if (
                validateInput(
                    event.title,
                    event.date,
                    event.city,
                    event.street,
                    event.status
                )
            ) {
                viewModelScope.launch {
                    editEventItemUseCase.editEventItem(event)
                }
            }
        }
    }

    fun getWeather(
        application: Application,
        address: String? = DEFAULT_ADDRESS,
        dateString: String? = changeDateFormat(CURRENT_BASE_DATE.toString())
    ) = viewModelScope.launch {

        val coordinate = getAddressFromLocation(address!!, application, GeoCoderHandler)
        val listCoordinate = coordinate.split(" ")
        val lat = listCoordinate[LATITUDE]
        val lon = listCoordinate[LONGITUDE]

        getWeatherUseCase.getWeather(lat, lon).let {  response ->

            if(response.isSuccessful) {

                val date2 = response.body()!!.forecasts[0].date

                diffBetweenDate = differenceBetweenDate(dateString, changeDateFormat(date2))
                if (diffBetweenDate > 7) {
                    throw RuntimeException(
                        "Exceeding the limit of the forecast interval. " +
                            "The forecast is only available for 7 days."
                    )
                } else {
                    _icon.postValue(response.body()?.fact?.icon)
                    _weatherTemp.postValue(response.body()!!.forecasts[diffBetweenDate].parts.day_short.temp)
                    _weatherDescription.postValue(response.body()!!.forecasts[diffBetweenDate].parts.day_short.condition)
                    _weatherResponse.postValue(response.body())
                }
            } else {
                Log.d("EventViewModel", "getWeather ERROR Response: ${response.message()}")
            }
        }
    }

    fun validateInput(
        title: String,
        date: String,
        city: String,
        street: String,
        status: String
    ): Boolean {
        Log.d("validateInput", status)

        var result = true
        if (title.isBlank()) {
            _errorInputTitle.value = true
            result = false
        }
        if (date.isBlank()) {
            _errorInputDate.value = true
            result = false
        }
        if (city.isBlank()) {
            _errorInputCity.value = true
            result = false
        }
        if (street.isBlank()) {
            _errorInputStreet.value = true
            result = false
        }
        return result
    }

    @SuppressLint("NewApi")
    private fun differenceBetweenDate(date1: String?, date2: String?): Int {
        val dateFormatInput = DateTimeFormatter.ofPattern("dd-MM-yyyy")

        Log.d("EventView", "date1: $date1")
        Log.d("EventView", "date2: $date2")

        val d1Parse = LocalDate.parse(date2, dateFormatInput)
        val d2Parse = LocalDate.parse(date1, dateFormatInput)

        Log.d("EventView", "d1Parse: $d1Parse")
        Log.d("EventView", "d2Parse: $d2Parse")

        val dDiff = ChronoUnit.DAYS.between(d1Parse, d2Parse)
        return dDiff.toInt()
    }

    private fun changeDateFormat(date: String?): String {
        val oldFormat =  SimpleDateFormat("yyyy-MM-dd")
        val newFormat = SimpleDateFormat("dd-MM-yyyy")
        val oldDate = oldFormat.parse(date)
        val newDate = newFormat.format(oldDate)
        return newDate
    }

    fun resetErrorInputTitle() {
        _errorInputTitle.postValue(false)
    }

    fun resetErrorInputDate() {
        _errorInputDate.postValue(false)
    }

    fun resetErrorInputCity() {
        _errorInputCity.postValue(false)
    }

    fun resetErrorInputStreet() {
        _errorInputStreet.postValue(false)
    }

    companion object {

        val CURRENT_BASE_DATE: LocalDate = LocalDate.now()
        const val DEFAULT_ADDRESS = "Moscow, Arbat"
        const val ADDRESS = "address"
        const val LATITUDE = 0
        const val LONGITUDE = 1
        const val UNDEFINED_DIFF = 0
        const val ICON_EXTENSION = ".svg"
    }
}