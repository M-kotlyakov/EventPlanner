package test.mertech.eventplanner.mvvm.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import test.mertech.eventplanner.mvvm.presentation.screens.ContactsScreen.ContactsFragment
import test.mertech.eventplanner.mvvm.presentation.screens.EventScreen.EventFragment
import test.mertech.eventplanner.mvvm.presentation.screens.MainScreen.MainActivity

@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(eventFragment: EventFragment)

    fun inject(contactsFragment: ContactsFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application
        ): ApplicationComponent
    }
}