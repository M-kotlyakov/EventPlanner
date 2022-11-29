package test.mertech.eventplanner.mvvm.presentation.screens.EventScreen

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import test.mertech.eventplanner.mvvm.presentation.EventPlannerApp
import test.mertech.eventplanner.mvvm.presentation.factory.ViewModelFactory
import javax.inject.Inject

class EventFragment: Fragment() {

    private lateinit var vm: EventViewModel

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
        vm = ViewModelProvider(this, viewModelFactory)[EventViewModel::class.java]

    }

    /*private lateinit var vm: EventViewModel

    private var screenMode: String = MODE_UNKNOWN
    private var eventItemId = Event.UNDEFINED_ID

    lateinit var onEditingFinishedListener: OnEditingFinishedListener

    private var _binding: FragmentEventBinding? = null
    private val binding: FragmentEventBinding
        get() = _binding ?: throw RuntimeException("FragmentShopItemBinding == null")

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is OnEditingFinishedListener) {
            onEditingFinishedListener = context
        } else {
            throw RuntimeException("Activity must implement onEditingFinishedListener")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        parseParams()
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
        launchRightMode()
    }

    private fun launchRightMode() {
        when (screenMode) {
            MODE_EDIT -> launchEditMode()
            MODE_ADD -> launchAddMode()
        }
    }

    private fun launchEditMode() {
        vm.getShopItem(eventItemId)
        binding.saveButton.setOnClickListener {
            vm.editShopItem(
                binding.etName.text?.toString(),
                binding. etCount.text?.toString()
            )
        }
    }

    private fun launchAddMode() {

        binding.saveButton.setOnClickListener {
            vm.addShopItem(
                binding.etName.text?.toString(),
                binding.etCount.text?.toString()
            )
        }
    }

    interface OnEditingFinishedListener {

        fun onEditingFinished()
    }

    fun parseParams() {
        val args = requireArguments()
        if(!args.containsKey(SCREEN_MODE)) {
            throw RuntimeException("Param screen mode is absent")
        }
        val mode = args.getString(SCREEN_MODE)
        if(mode != MODE_EDIT && mode != MODE_ADD) {
            throw RuntimeException("unknown screen mode")
        }
        screenMode = mode
        if(screenMode == MODE_EDIT) {
            if(!args.containsKey(EVENT_ITEM_ID)) {
                throw RuntimeException("Param shop item id is absent")
            }
            shopItemId = args.getInt(EVENT_ITEM_ID, Event.UNDEFINED_ID)
        }
    }*/

    companion object {

        private const val SCREEN_MODE = "extra_mode"
        private const val EVENT_ITEM_ID = "extra_event_item_id"
        private const val MODE_ADD = "mode_add"
        private const val MODE_EDIT = "mode_edit"
        private const val MODE_UNKNOWN = ""


        fun newInstanceAddItem() : EventFragment {
            return EventFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_ADD)
                }
            }
        }

        fun newInstanceEditItem(eventItemId: Int) : EventFragment {
            return EventFragment().apply {
                arguments = Bundle().apply {
                    putString(SCREEN_MODE, MODE_EDIT)
                    putInt(EVENT_ITEM_ID, eventItemId)
                }
            }
        }
    }
}