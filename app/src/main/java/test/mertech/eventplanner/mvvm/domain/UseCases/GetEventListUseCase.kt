package test.mertech.eventplanner.mvvm.domain.UseCases

import androidx.lifecycle.LiveData
import test.mertech.eventplanner.mvvm.domain.repository.EventPlannerRepository
import test.mertech.eventplanner.mvvm.domain.entity.Event
import javax.inject.Inject

class GetEventListUseCase @Inject constructor(
    private val eventPlannerRepository: EventPlannerRepository
) {
    fun getEventList(): LiveData<List<Event>> {
        return eventPlannerRepository.getEventList()
    }
}