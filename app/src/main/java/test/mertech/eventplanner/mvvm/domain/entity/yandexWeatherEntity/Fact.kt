package test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity

data class Fact(
    val cloudness: Double,
    val condition: String,
    val daytime: String,
    val feels_like: Double,
    val humidity: Double,
    val icon: String,
    val is_thunder: Boolean,
    val obs_time: Double,
    val phenom_condition: String,
    val phenom_icon: String,
    val polar: Boolean,
    val prec_strength: Double,
    val prec_type: Double,
    val pressure_mm: Double,
    val pressure_pa: Double,
    val season: String,
    val temp: Double,
    val wind_dir: String,
    val wind_gust: Double,
    val wind_speed: Double
)