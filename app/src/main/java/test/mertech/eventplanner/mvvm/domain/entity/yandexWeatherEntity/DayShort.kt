package test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity

data class DayShort(
    val cloudness: Double,
    val condition: String,
    val feels_like: Double,
    val humidity: Double,
    val icon: String,
    val prec_strength: Double,
    val prec_type: Double,
    val pressure_mm: Double,
    val pressure_pa: Double,
    val temp: Double,
    val temp_min: Double,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)