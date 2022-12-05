package test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity

data class Hour(
    val cloudness: Double,
    val condition: String,
    val feels_like: Double,
    val hour: String,
    val hour_ts: Double,
    val humidity: Double,
    val icon: String,
    val is_thunder: Boolean,
    val prec_mm: Double,
    val prec_period: Double,
    val prec_strength: Double,
    val prec_type: Double,
    val pressure_mm: Double,
    val pressure_pa: Double,
    val temp: Double,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)