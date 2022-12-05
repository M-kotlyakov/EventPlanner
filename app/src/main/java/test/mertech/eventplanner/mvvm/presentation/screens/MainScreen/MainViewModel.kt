package test.mertech.eventplanner.mvvm.presentation.screens.MainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import test.mertech.eventplanner.mvvm.data.database.entity.EventDbModel
import test.mertech.eventplanner.mvvm.data.mapper.EventPlannerMapper
import test.mertech.eventplanner.mvvm.domain.UseCases.AddEventUseCase
import test.mertech.eventplanner.mvvm.domain.UseCases.DeleteEventItemUseCase
import test.mertech.eventplanner.mvvm.domain.UseCases.EditEventItemUseCase
import test.mertech.eventplanner.mvvm.domain.UseCases.GetEventListUseCase
import test.mertech.eventplanner.mvvm.domain.entity.Event
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val getEventListUseCase: GetEventListUseCase,
    private val deleteEventItemUseCase: DeleteEventItemUseCase,
    private val mapper: EventPlannerMapper
): ViewModel() {

    val eventList = getEventListUseCase.getEventList()

    fun deleteEventItem(event: Event) {
        viewModelScope.launch {
            deleteEventItemUseCase.deleteEventItem(event)
        }
    }
}