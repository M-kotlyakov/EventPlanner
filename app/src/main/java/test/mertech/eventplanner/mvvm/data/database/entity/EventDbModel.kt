package test.mertech.eventplanner.mvvm.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "event_items")
data class EventDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val title: String,
    val description: String,
    val date: String,
    val country: String,
    val city: String,
    val street: String,
    val house: String,
    val blocks: String? = null,
    val status: String,
    val imageUrl: String? = null
)
