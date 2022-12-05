package test.mertech.eventplanner.mvvm.data.mapper

import test.mertech.eventplanner.mvvm.data.database.entity.EventDbModel
import test.mertech.eventplanner.mvvm.domain.entity.Event
import javax.inject.Inject

class EventPlannerMapper @Inject constructor() {

    fun mapEventEntityToDbModel(event: Event) = EventDbModel(
        id = event.id,
        title = event.title,
        description = event.description,
        date = event.date,
        city = event.city,
        street = event.street,
        house = event.house,
        status = event.status,
        celsius = event.celsius,
        weather_description = event.weather_description,
        imageUrl = event.imageUrl
    )

    fun mapEventDbModelToEntity(eventDbModel: EventDbModel) = Event(
        id = eventDbModel.id,
        title = eventDbModel.title,
        description = eventDbModel.description,
        date = eventDbModel.date,
        city = eventDbModel.city,
        street = eventDbModel.street,
        house = eventDbModel.house,
        status = eventDbModel.status,
        celsius = eventDbModel.celsius,
        weather_description = eventDbModel.weather_description,
        imageUrl = eventDbModel.imageUrl
    )

    fun mapEventListDbModelToListEntity(list: List<EventDbModel>) = list.map {
        mapEventDbModelToEntity(it)
    }
}