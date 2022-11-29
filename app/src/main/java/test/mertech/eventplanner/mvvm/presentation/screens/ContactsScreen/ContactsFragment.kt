package test.mertech.eventplanner.mvvm.presentation.screens.ContactsScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import test.mertech.eventplanner.mvvm.presentation.EventPlannerApp
import test.mertech.eventplanner.mvvm.presentation.factory.ViewModelFactory
import test.mertech.eventplanner.mvvm.presentation.screens.EventScreen.EventViewModel
import javax.inject.Inject

class ContactsFragment: Fragment() {

    private lateinit var vm: ContactsViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EventPlannerApp).component
    }

    override fun onAttach(context: Context) {
        component.inject(this)
        super.onAttach(context)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this, viewModelFactory)[ContactsViewModel::class.java]

    }
}