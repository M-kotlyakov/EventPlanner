package test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity

data class WeatherYandex(
    val fact: Fact,
    val forecasts: List<Forecast>,
    val info: Info,
    val now: Int,
    val now_dt: String
)