package test.mertech.eventplanner.mvvm.di

import android.app.Application
import dagger.BindsInstance
import dagger.Component
import test.mertech.eventplanner.mvvm.presentation.screens.eventScreen.EventFragment
import test.mertech.eventplanner.mvvm.presentation.screens.MainScreen.MainActivity
import test.mertech.eventplanner.mvvm.presentation.screens.MainScreen.MainFragment
import test.mertech.eventplanner.mvvm.presentation.screens.SeeEventFRagment.SeeEventFragment

@ApplicationScope
@Component(
    modules = [DataModule::class, ViewModelModule::class]
)
interface ApplicationComponent {

    fun inject(mainActivity: MainActivity)

    fun inject(eventFragment: EventFragment)

    fun inject(mainFragment: MainFragment)

    fun inject(seeEventFragment: SeeEventFragment)

    @Component.Factory
    interface Factory {

        fun create(
            @BindsInstance application: Application,
            @BindsInstance BASE_URL: String
        ): ApplicationComponent
    }
}