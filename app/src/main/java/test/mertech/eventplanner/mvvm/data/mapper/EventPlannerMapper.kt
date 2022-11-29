package test.mertech.eventplanner.mvvm.data.mapper

import test.mertech.eventplanner.mvvm.data.database.entity.ContactsDbModel
import test.mertech.eventplanner.mvvm.data.database.entity.EventDbModel
import test.mertech.eventplanner.mvvm.domain.entity.Contacts
import test.mertech.eventplanner.mvvm.domain.entity.Event
import javax.inject.Inject

class EventPlannerMapper @Inject constructor() {

    fun mapEventEntityToDbModel(event: Event) = EventDbModel(
        id = event.id,
        title = event.title,
        description = event.description,
        date = event.date,
        country = event.country,
        city = event.city,
        street = event.street,
        house = event.house,
        blocks = event.blocks,
        status = event.status
    )

    fun mapContactsEntityToDbModel(contacts: Contacts) = ContactsDbModel(
        id = contacts.id,
        name = contacts.name,
        surName = contacts.surName,
        jobTitle = contacts.jobTitle,
        phoneNumber = contacts.phoneNumber
    )

    fun mapEventDbModelToEntity(eventDbModel: EventDbModel) = Event(
        id = eventDbModel.id,
        title = eventDbModel.title,
        description = eventDbModel.description,
        date = eventDbModel.date,
        country = eventDbModel.country,
        city = eventDbModel.city,
        street = eventDbModel.street,
        house = eventDbModel.house,
        blocks = eventDbModel.blocks,
        status = eventDbModel.status
    )

    fun mapContactsDbModelToEntity(contactsDbModel: ContactsDbModel) = Contacts(
        id = contactsDbModel.id,
        name = contactsDbModel.name,
        surName = contactsDbModel.surName,
        jobTitle = contactsDbModel.jobTitle,
        phoneNumber = contactsDbModel.phoneNumber
    )

    fun mapEventListDbModelToListEntity(list: List<EventDbModel>) = list.map {
        mapEventDbModelToEntity(it)
    }

    fun mapContactsListDbModelToListEntity(list: List<ContactsDbModel>) = list.map {
        mapContactsDbModelToEntity(it)
    }
}