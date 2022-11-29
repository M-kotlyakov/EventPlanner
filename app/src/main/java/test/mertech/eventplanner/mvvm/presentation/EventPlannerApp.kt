package test.mertech.eventplanner.mvvm.presentation

import android.app.Application
import test.mertech.eventplanner.mvvm.di.DaggerApplicationComponent

class EventPlannerApp: Application() {

    val component by lazy {
        DaggerApplicationComponent.factory().create(this)
    }
}