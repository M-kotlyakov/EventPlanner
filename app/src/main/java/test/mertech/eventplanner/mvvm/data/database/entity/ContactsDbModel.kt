package test.mertech.eventplanner.mvvm.data.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "contact_items")
data class ContactsDbModel(
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val surName:String,
    val jobTitle: String,
    val phoneNumber: String
)