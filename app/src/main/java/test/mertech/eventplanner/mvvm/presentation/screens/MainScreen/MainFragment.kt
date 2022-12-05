package test.mertech.eventplanner.mvvm.presentation.screens.MainScreen

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import test.mertech.eventplanner.R
import test.mertech.eventplanner.databinding.FragmentMainBinding
import test.mertech.eventplanner.mvvm.presentation.EventPlannerApp
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.MAX_POOL_SIZE
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.VIEW_EXPECTED_VALUE
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.VIEW_MISSED_VALUE
import test.mertech.eventplanner.mvvm.presentation.adapter.EventAdapter.Companion.VIEW_VISITED_VALUE
import test.mertech.eventplanner.mvvm.presentation.factory.ViewModelFactory
import test.mertech.eventplanner.mvvm.presentation.screens.eventScreen.EventFragment
import javax.inject.Inject

class MainFragment: Fragment() {

    private lateinit var vm: MainViewModel
    private lateinit var eventAdapter: EventAdapter

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EventPlannerApp).component
    }

    private var _binding: FragmentMainBinding? = null
    private val binding: FragmentMainBinding
        get() = _binding ?: throw RuntimeException("FragmentMainBinding == null")

    override fun onAttach(context: Context) {
        component.inject(mainFragment = this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        vm = ViewModelProvider(this,viewModelFactory)[MainViewModel::class.java]
        vm.eventList.observe(viewLifecycleOwner) {
            eventAdapter.submitList(it)
        }
        setupSwipeListener(binding.rvEventPlannerList)
        addEventItemClick()
        itemClick()
    }

    private fun setupRecyclerView() {
        with(binding.rvEventPlannerList) {
            eventAdapter = EventAdapter()
            adapter = eventAdapter
            recycledViewPool.setMaxRecycledViews(VIEW_EXPECTED_VALUE, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_VISITED_VALUE, MAX_POOL_SIZE)
            recycledViewPool.setMaxRecycledViews(VIEW_MISSED_VALUE, MAX_POOL_SIZE)
        }
    }

    private fun itemClick() {
        eventAdapter.onEventItemClickListener =  {
            findNavController().navigate(
                MainFragmentDirections.actionMainFragmentToSeeEventFragment(it)
            )
        }
        eventAdapter.onEditEventItemClickListener =  {
            val bundle = bundleOf(
                EventFragment.EXTRA_MODE to EventFragment.EDIT_MODE,
                EventFragment.EDIT_MODE to it
            )
            Log.d("EventAdapter", "MainFragment:: title: ${it.title}, status: ${it.status}")
            findNavController().navigate(
                R.id.action_mainFragment_to_eventFragment, bundle
            )
        }
    }

    private fun setupSwipeListener(recyclerView: RecyclerView) {
        val callback = object : ItemTouchHelper.SimpleCallback(
            0,
            ItemTouchHelper.LEFT or ItemTouchHelper.RIGHT
        ) {
            override fun onMove(
                recyclerView: RecyclerView,
                viewHolder: RecyclerView.ViewHolder,
                target: RecyclerView.ViewHolder
            ): Boolean {
                return false
            }

            override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
                val item = eventAdapter.currentList[viewHolder.adapterPosition]
                vm.deleteEventItem(item)
            }
        }
        val itemTouchHelper = ItemTouchHelper(callback)
        itemTouchHelper.attachToRecyclerView(recyclerView)
    }

    private fun addEventItemClick() {
        val bundle = bundleOf(EventFragment.EXTRA_MODE to EventFragment.ADD_MODE)
        binding.buttonAddEventItem.setOnClickListener {

            findNavController().navigate(R.id.action_mainFragment_to_eventFragment, bundle)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}