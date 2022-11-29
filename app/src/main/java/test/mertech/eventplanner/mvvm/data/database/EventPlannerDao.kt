package test.mertech.eventplanner.mvvm.data.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import test.mertech.eventplanner.mvvm.data.database.entity.EventDbModel

@Dao
interface EventPlannerDao {

    @Query("SELECT * FROM event_items")
    fun getEventList(): LiveData<List<EventDbModel>>

    @Query("SELECT * FROM event_items WHERE id =:eventItemId LIMIT 1")
    suspend fun getShopItem(eventItemId: Int): EventDbModel

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addEventItem(eventItemDbModel: EventDbModel)

    @Query("DELETE FROM event_items WHERE id = :eventItemId")
    suspend  fun deleteEventItem(eventItemId: Int)
}