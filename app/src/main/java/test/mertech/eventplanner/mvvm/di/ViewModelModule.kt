package test.mertech.eventplanner.mvvm.di

import androidx.lifecycle.ViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
import test.mertech.eventplanner.mvvm.presentation.screens.ContactsScreen.ContactsViewModel
import test.mertech.eventplanner.mvvm.presentation.screens.EventScreen.EventViewModel
import test.mertech.eventplanner.mvvm.presentation.screens.MainScreen.MainViewModel

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
    @ViewModelKey(ContactsViewModel::class)
    @Binds
    fun bindContactsViewModel(impl: ContactsViewModel): ViewModel
}