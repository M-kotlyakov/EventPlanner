package test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity

data class Forecast(
    val date: String,
    val date_ts: Int,
    val hours: List<Hour>,
    val moon_code: Int,
    val moon_text: String,
    val parts: Parts,
    val sunrise: String,
    val sunset: String,
    val week: Int
)