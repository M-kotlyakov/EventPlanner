package test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity

data class Info(
    val def_pressure_mm: Double,
    val def_pressure_pa: Double,
    val lat: Double,
    val lon: Double,
    val tzinfo: Tzinfo,
    val url: String
)