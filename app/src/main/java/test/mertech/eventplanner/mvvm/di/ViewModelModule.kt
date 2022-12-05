package test.mertech.eventplanner.mvvm.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import test.mertech.eventplanner.mvvm.presentation.screens.eventScreen.EventViewModel
import test.mertech.eventplanner.mvvm.presentation.screens.MainScreen.MainViewModel
import test.mertech.eventplanner.mvvm.presentation.screens.SeeEventFRagment.SeeEventViewModel

@Module
interface ViewModelModule {

    @IntoMap
    @ViewModelKey(MainViewModel::class)
    @Binds
    fun bindMainViewModel(impl: MainViewModel): ViewModel

    @IntoMap
    @ViewModelKey(EventViewModel::class)
    @Binds
    fun bindEventViewModel(impl: EventViewModel): ViewModel

    @IntoMap
    @ViewModelKey(SeeEventViewModel::class)
    @Binds
    fun bindSeeEventViewModel(impl: SeeEventViewModel): ViewModel
}