package test.mertech.eventplanner.mvvm.domain

import androidx.lifecycle.LiveData
import test.mertech.eventplanner.mvvm.domain.entity.Event

interface EventPlannerRepository {

    suspend fun addEventItem(event: Event)

    suspend fun deleteEventItem(event: Event)

    suspend fun editEventItem(event: Event)

    suspend fun getEventItem(eventItemId: Int): Event

    fun getEventList(): LiveData<List<Event>>
}