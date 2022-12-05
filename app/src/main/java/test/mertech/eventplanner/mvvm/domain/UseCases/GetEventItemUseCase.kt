package test.mertech.eventplanner.mvvm.domain.UseCases

import test.mertech.eventplanner.mvvm.domain.repository.EventPlannerRepository
import test.mertech.eventplanner.mvvm.domain.entity.Event
import javax.inject.Inject

class GetEventItemUseCase @Inject constructor(
    private val eventPlannerRepository: EventPlannerRepository
) {
    suspend fun getEventItem(eventItemId: Int): Event {
       return eventPlannerRepository.getEventItem(eventItemId)
    }
}