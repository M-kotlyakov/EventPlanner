package test.mertech.eventplanner.mvvm.presentation.screens.MainScreen

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import test.mertech.eventplanner.R
import test.mertech.eventplanner.databinding.ActivityMainBinding
import test.mertech.eventplanner.mvvm.presentation.EventPlannerApp
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.MAX_POOL_SIZE
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.VIEW_DEFAULT_VALUE
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.VIEW_MISSED_VALUE
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.VIEW_VISITED_VALUE
import test.mertech.eventplanner.mvvm.presentation.factory.ViewModelFactory
import test.mertech.eventplanner.mvvm.presentation.screens.EventScreen.EventFragment
import javax.inject.Inject

class MainActivity : AppCompatActivity() {

    private lateinit var vm: MainViewModel
    private lateinit var eventAdapter: EventAdapter
    private lateinit var binding: ActivityMainBinding

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as EventPlannerApp).component
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupRecyclerView()
        vm = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        vm.eventList.observe(this) {
            eventAdapter.submitList(it)
        }
    }

    private fun launchFragment(fragment: Fragment) {
        supportFragmentManager.popBackStack()
        supportFragmentManager.beginTransaction()
            .replace(R.id.event_item_container, fragment)
            .addToBackStack(null)
            .commit()
    }

    private fun setupRecyclerView() {
        with(binding.rvShopList) {
            eventAdapter = EventAdapter()
            adapter = eventAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_DEFAULT_VALUE, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_VISITED_VALUE, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_MISSED_VALUE, MAX_POOL_SIZE)
        }
        setupClickListener()
    }

    private fun isOnePaneMode(): Boolean = binding.eventItemContainer == null

    private fun setupClickListener() {
        eventAdapter.onEventItemClickListener = {
            if (isOnePaneMode()) {
//                launchFragment(EventFragment.newInstanceEditItem(it.id))
            } else {
//                launchFragment(EventFragment.newInstanceEditItem(it.id))
            }
        }
    }
}