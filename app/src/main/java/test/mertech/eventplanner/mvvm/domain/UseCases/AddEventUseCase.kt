package test.mertech.eventplanner.mvvm.domain.UseCases

import test.mertech.eventplanner.mvvm.domain.EventPlannerRepository
import test.mertech.eventplanner.mvvm.domain.entity.Event
import javax.inject.Inject

class AddEventUseCase @Inject constructor(
    private val eventPlannerRepository: EventPlannerRepository
) {
    suspend fun addEventItem(event: Event) {
        eventPlannerRepository.addEventItem(event)
    }
}