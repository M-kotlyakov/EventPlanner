package test.mertech.eventplanner.mvvm.domain.entity

import java.io.Serializable

data class Event(
    val id: Int = UNDEFINED_ID,
    val title: String,
    val description: String,
    val date: String,
    val city: String,
    val street: String,
    val house: String,
    val status: String,
    val celsius: String,
    val weather_description: String,
    val imageUrl: String? = null
): Serializable {

    companion object {

        const val UNDEFINED_ID = 0
    }
}