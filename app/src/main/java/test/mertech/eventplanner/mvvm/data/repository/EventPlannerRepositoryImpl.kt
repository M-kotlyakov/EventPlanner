package test.mertech.eventplanner.mvvm.data.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import retrofit2.Response
import test.mertech.eventplanner.mvvm.data.database.EventPlannerDao
import test.mertech.eventplanner.mvvm.data.mapper.EventPlannerMapper
import test.mertech.eventplanner.mvvm.data.network.api.ApiService
import test.mertech.eventplanner.mvvm.domain.entity.Event
import test.mertech.eventplanner.mvvm.domain.entity.yandexWeatherEntity.WeatherYandex
import test.mertech.eventplanner.mvvm.domain.repository.EventPlannerRepository
import javax.inject.Inject

class EventPlannerRepositoryImpl @Inject constructor(
    private val eventPlannerDao: EventPlannerDao,
    private val mapper: EventPlannerMapper,
    private val apiService: ApiService
): EventPlannerRepository {

    override suspend fun getWeather(lat: String, lon: String): Response<WeatherYandex> = apiService.getWeather(lat, lon)

    override suspend fun addEventItem(event: Event) {
        eventPlannerDao.addEventItem(mapper.mapEventEntityToDbModel(event))
    }

    override suspend fun deleteEventItem(event: Event) {
        eventPlannerDao.deleteEventItem(event.id)
    }

    override suspend fun editEventItem(event: Event) {
        eventPlannerDao.addEventItem(mapper.mapEventEntityToDbModel(event))
    }

    override suspend fun getEventItem(eventItemId: Int): Event {
        val dbModel = eventPlannerDao.getShopItem(eventItemId)
        return mapper.mapEventDbModelToEntity(dbModel)
    }

    override fun getEventList(): LiveData<List<Event>> = Transformations.map(
        eventPlannerDao.getEventList()
    ) {
        mapper.mapEventListDbModelToListEntity(it)
    }
}