package test.mertech.eventplanner.mvvm.presentation.screens.eventScreen

import android.content.Context
import android.os.Bundle
import android.provider.ContactsContract.QuickContact.EXTRA_MODE
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import test.mertech.eventplanner.R
import test.mertech.eventplanner.databinding.FragmentEventBinding
import test.mertech.eventplanner.mvvm.domain.entity.Event
import test.mertech.eventplanner.mvvm.presentation.EventPlannerApp
import test.mertech.eventplanner.mvvm.presentation.factory.ViewModelFactory
import test.mertech.eventplanner.mvvm.presentation.screens.eventScreen.EventViewModel.Companion.DEFAULT_ADDRESS
import test.mertech.eventplanner.mvvm.presentation.screens.eventScreen.EventViewModel.Companion.ICON_EXTENSION
import test.mertech.eventplanner.mvvm.presentation.utils.loadSvg
import javax.inject.Inject

class EventFragment : Fragment() {

    private lateinit var vm: EventViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EventPlannerApp).component
    }

    private var _binding: FragmentEventBinding? = null
    private val binding: FragmentEventBinding
        get() = _binding ?: throw RuntimeException("FragmentWelcomeBinding == null")

    override fun onAttach(context: Context) {
        component.inject(eventFragment = this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this, viewModelFactory)[EventViewModel::class.java]
        binding.vm = vm
        binding.lifecycleOwner = viewLifecycleOwner

        observeViewModel()
        getParams()
        addTextChangeListener()
    }

//    private fun setupSpinner(): String {
//        var spinnerSelected = ""
//        val spinner = binding.spinner
//        val status = resources.getStringArray(R.array.status)
//        val spinnerAdapter = ArrayAdapter(
//            requireActivity().application,
//            android.R.layout.simple_spinner_item,
//            status
//        )
//        spinner.adapter = spinnerAdapter
//        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
//                Log.d("EventFragment", "status[position] = ${status[position]}")
//                spinnerSelected = status[position]
//                Log.d("EventFragment", "onItemSelected spinnerSelected = $spinnerSelected")
//            }
//
//            override fun onNothingSelected(p0: AdapterView<*>?) {
//                TODO("Not yet implemented")
//            }
//        }
//        Log.d("EventFragment", "spinnerSelected = $spinnerSelected")
//        return spinnerSelected
//    }

    private fun getParams() {
        var item: Event? = null
        val args = arguments?.getString(EXTRA_MODE)

        try {
            item = arguments?.getSerializable(EDIT_MODE) as Event
            setFields(item)
        } catch (e: NullPointerException) {
            null
        }
        when(args) {
            ADD_MODE -> launchAddMode()
            EDIT_MODE -> item?.let { launchEditMode(it) }
        }
    }

    private fun setFields(args: Event) {
        with(binding) {
            inputEditTextTitle.setText(args.title)
            inputEditTextDescription.setText(args.description)
            inputEditTextDate.setText(args.date)
            inputEditTextCity.setText(args.city)
            inputEditTextStreet.setText(args.street)
            inputEditTextHouse.setText(args.house)
        }
    }

    private fun launchEditMode(args: Event) {

        vm.getWeather(
            dateString = args.date,
            address = "${args.city}, ${args.street}",
            application = requireActivity().application
        )

        var spinnerSelected = args.status
        val spinner = binding.spinner
        val status = resources.getStringArray(R.array.status)
        val spinnerAdapter = ArrayAdapter(
            requireActivity().application,
            android.R.layout.simple_spinner_item,
            status
        )
        spinner.adapter = spinnerAdapter
        when(args.status) {
            EXPECTED -> spinner.setSelection(EXPECTED_POSITION)
            VISITED -> spinner.setSelection(VISITED_POSITION)
            MISSED -> spinner.setSelection(MISSED_POSITION)
        }
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                spinnerSelected = status[position]
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.saveBtn.setOnClickListener {

            if (
                vm.validateInput(
                    binding.inputEditTextTitle.text.toString(),
                    binding.inputEditTextDate.text.toString(),
                    binding.inputEditTextCity.text.toString(),
                    binding.inputEditTextStreet.text.toString(),
                    spinnerSelected
                )
            ) {
                val item = args.copy(
                    title = binding.inputEditTextTitle.text.toString(),
                    description = binding.inputEditTextDescription.text.toString(),
                    date = binding.inputEditTextDate.text.toString(),
                    city = binding.inputEditTextCity.text.toString(),
                    street = binding.inputEditTextStreet.text.toString(),
                    house = binding.inputEditTextHouse.text.toString(),
                    status = spinnerSelected
                )
                vm.editEventItem(item)
                findNavController().navigate(R.id.action_eventFragment_to_mainFragment)
            }
        }
    }

    private fun launchAddMode() {
        vm.getWeather(
            application = requireActivity().application
        )

        var spinnerSelected = ""
        val spinner = binding.spinner
        val status = resources.getStringArray(R.array.status)
        val spinnerAdapter = ArrayAdapter(
            requireActivity().application,
            android.R.layout.simple_spinner_item,
            status
        )
        spinner.adapter = spinnerAdapter
        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                Log.d("EventFragment", "status[position] = ${status[position]}")
                spinnerSelected = status[position]
                Log.d("EventFragment", "onItemSelected spinnerSelected = $spinnerSelected")
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                TODO("Not yet implemented")
            }
        }

        binding.saveBtn.setOnClickListener {
            if (
                vm.validateInput(
                    binding.inputEditTextTitle.text.toString(),
                    binding.inputEditTextDate.text.toString(),
                    binding.inputEditTextCity.text.toString(),
                    binding.inputEditTextStreet.text.toString(),
                    spinnerSelected
                )
            ) {
                vm.addEventItem(
                    binding.inputEditTextTitle.text.toString(),
                    binding.inputEditTextDescription.text.toString(),
                    binding.inputEditTextDate.text.toString(),
                    binding.inputEditTextCity.text.toString(),
                    binding.inputEditTextStreet.text.toString(),
                    binding.inputEditTextHouse.text.toString(),
                    spinnerSelected
                )
                turnBack()
            }
        }
    }

    private fun turnBack() {
        findNavController().navigate(R.id.action_eventFragment_to_mainFragment)
    }

    private fun observeViewModel() {
        vm.weatherResponse.observe(viewLifecycleOwner) { weather ->

            val address = if (binding.inputEditTextCity.text.toString().isEmpty() && binding.inputEditTextStreet.text.toString().isEmpty()) {
                DEFAULT_ADDRESS
            } else {
                "${binding.inputEditTextCity.text.toString()}, ${binding.inputEditTextStreet.text.toString()}"
            }
            binding.tvAddress.text = address
            binding.tvWeatherDate.text = weather.forecasts[vm.diffBetweenDate].date
        }
        vm.weatherTemp.observe(viewLifecycleOwner) { temp ->
            binding.tvTemp.text = String.format("%s °C", temp)
        }
        vm.weatherDescription.observe(viewLifecycleOwner) { description ->
            binding.tvDescription.text = description
        }
        vm.icon.observe(viewLifecycleOwner) { icon ->
            binding.tvImageWeather.loadSvg(BASE_ICON_URL + icon + ICON_EXTENSION)
        }
    }

    private fun addTextChangeListener() {
        binding.inputEditTextTitle.textChangeListener { vm.resetErrorInputTitle() }
        binding.inputEditTextDate.textChangeListener { vm.resetErrorInputDate() }
        binding.inputEditTextCity.textChangeListener { vm.resetErrorInputCity() }
        binding.inputEditTextStreet.textChangeListener { vm.resetErrorInputStreet() }
    }

    private fun EditText.textChangeListener(block: () -> Unit) {
        addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                block()
            }

            override fun afterTextChanged(p0: Editable?) {
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {

        const val BASE_ICON_URL = "https://yastatic.net/weather/i/icons/funky/dark/"
        const val ICON_EXTENSION = ".svg"

        const val EXTRA_MODE = "extra_mode"
        const val ADD_MODE = "add_mode"
        const val EDIT_MODE = "edit_mode"

        const val EXPECTED = "expected"
        const val VISITED = "visited"
        const val MISSED = "missed"

        const val EXPECTED_POSITION = 0
        const val VISITED_POSITION  = 1
        const val MISSED_POSITION  = 2
    }
}