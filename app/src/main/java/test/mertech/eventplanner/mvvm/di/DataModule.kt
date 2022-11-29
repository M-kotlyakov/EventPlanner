package test.mertech.eventplanner.mvvm.di

import android.app.Application
import dagger.Binds
import dagger.Module
import dagger.Provides
import test.mertech.eventplanner.mvvm.data.database.AppDataBase
import test.mertech.eventplanner.mvvm.data.database.EventPlannerDao
import test.mertech.eventplanner.mvvm.data.repository.EventPlannerRepositoryImpl
import test.mertech.eventplanner.mvvm.domain.EventPlannerRepository

@Module
interface DataModule {

    @ApplicationScope
    @Binds
    fun bindEventPlannerRepository(impl: EventPlannerRepositoryImpl): EventPlannerRepository

    companion object {

        @ApplicationScope
        @Provides
        fun provideListDao(
            application: Application
        ): EventPlannerDao {
            return AppDataBase.getInstance(application).eventPlannerDao()
        }
    }
}