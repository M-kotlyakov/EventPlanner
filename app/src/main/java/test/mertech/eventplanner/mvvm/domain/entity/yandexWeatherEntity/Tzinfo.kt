package test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity

data class Tzinfo(
    val abbr: String,
    val dst: Boolean,
    val name: String,
    val offset: Double
)