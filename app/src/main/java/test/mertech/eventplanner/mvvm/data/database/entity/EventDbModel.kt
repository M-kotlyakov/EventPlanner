package test.mertech.eventplanner.mvvm.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "event_items")
data class EventDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
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
): Serializable
