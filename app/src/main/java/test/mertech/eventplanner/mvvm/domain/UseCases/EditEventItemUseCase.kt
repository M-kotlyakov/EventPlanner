package test.mertech.eventplanner.mvvm.domain.UseCases

import test.mertech.eventplanner.mvvm.domain.repository.EventPlannerRepository
import test.mertech.eventplanner.mvvm.domain.entity.Event
import javax.inject.Inject

class EditEventItemUseCase @Inject constructor(
    private val eventPlannerRepository: EventPlannerRepository
) {
    suspend fun editEventItem(event: Event) {
        eventPlannerRepository.editEventItem(event)
    }
}