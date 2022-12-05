package test.mertech.eventplanner.mvvm.presentation.screens.SeeEventFRagment

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import test.mertech.eventplanner.R
import test.mertech.eventplanner.databinding.FragmentSeeEventBinding
import test.mertech.eventplanner.mvvm.presentation.EventPlannerApp
import test.mertech.eventplanner.mvvm.presentation.factory.ViewModelFactory
import test.mertech.eventplanner.mvvm.presentation.utils.loadSvg
import javax.inject.Inject


class SeeEventFragment: Fragment() {

    private val args by navArgs<SeeEventFragmentArgs>()

    private lateinit var vm: SeeEventViewModel

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (requireActivity().application as EventPlannerApp).component
    }

    private var _binding: FragmentSeeEventBinding? = null
    private val binding: FragmentSeeEventBinding
        get() = _binding ?: throw RuntimeException("FragmentSeeEventBinding == null")

    override fun onAttach(context: Context) {
        component.inject(seeEventFragment = this)
        super.onAttach(context)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSeeEventBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        vm = ViewModelProvider(this, viewModelFactory)[SeeEventViewModel::class.java]

        observeViewModel()
        initViewsFromArgs()
        clickBackButton()
    }

    private fun initViewsFromArgs() {
        Log.d("EventAdapter", "SeeEventFragment:: item: ${args.eventItem}")
        val item = args.eventItem
        with(binding) {
            tvTitle.text = item.title
            tvDescr.text = item.description
            tvWeatherDate.text = item.date
            tvDress.text = "${item.city}, ${item.street}, ${item.house}"
            tvStatus.text = item.status
            tvTemp.text = item.celsius
            tvDescription.text = item.weather_description
            tvImageWeather.loadSvg(item.imageUrl!!)
        }
        Log.d("EventAdapter", "${item.city}, ${item.street}, ${item.house}")
    }

    private fun observeViewModel() {
        vm.weatherResponse.observe(viewLifecycleOwner) { weather ->
            binding.tvWeatherDate.text = weather.forecasts[0].date
            binding.tvTemp.text = String.format( "%s °C", weather.forecasts[0].parts.day_short.temp.toString())
        }
    }

    private fun clickBackButton() {
        binding.turnBackBtn.setOnClickListener {
            findNavController().navigate(R.id.action_seeEventFragment_to_mainFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}