package test.mertech.eventplanner.mvvm.presentation.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.recyclerview.widget.ListAdapter
import test.mertech.eventplanner.R
import test.mertech.eventplanner.databinding.ItemEventExpectedBinding
import test.mertech.eventplanner.databinding.ItemEventMissedBinding
import test.mertech.eventplanner.databinding.ItemEventVisitedBinding
import test.mertech.eventplanner.mvvm.domain.entity.Event

class EventAdapter : ListAdapter<Event, EventViewHolder>(EventDiffCallback()){

    var onEventItemClickListener: ((Event) -> Unit)? = null
    var onEditEventItemClickListener: ((Event) -> Unit)? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EventViewHolder {
        val layout = when(viewType) {
            VIEW_EXPECTED_VALUE -> R.layout.item_event_expected
            VIEW_VISITED_VALUE -> R.layout.item_event_visited
            VIEW_MISSED_VALUE -> R.layout.item_event_missed
            else -> throw RuntimeException("Unknown view type $viewType")
        }
        val binding = DataBindingUtil.inflate<ViewDataBinding>(
            LayoutInflater.from(parent.context),
            layout,
            parent,
            false
        )
        return EventViewHolder(binding)
    }

    override fun onBindViewHolder(viewHolder: EventViewHolder, position: Int) {
        val item = getItem(position)
        val binding = viewHolder.binding
        binding.root.setOnClickListener {
            Log.d("EventAdapter", "title: ${item.title}, status: ${item.status}")
            onEventItemClickListener?.invoke(item)
        }
        when(binding) {
            is ItemEventExpectedBinding -> {
                with(binding) {
                    tvTitle.text = item.title
                    tvStatusValue.text = item.status
                    tvDate.text = item.date

                }
                binding.editBtn.setOnClickListener {
                    onEditEventItemClickListener?.invoke(item)
                }
            }
            is ItemEventVisitedBinding -> {
                with(binding) {
                    tvTitle.text = item.title
                    tvStatusValue.text = item.status
                    tvDate.text = item.date
                }
                binding.editBtn.setOnClickListener {
                    onEditEventItemClickListener?.invoke(item)
                }
            }
            is ItemEventMissedBinding -> {
                with(binding) {
                    tvTitle.text = item.title
                    tvStatusValue.text = item.status
                    tvDate.text = item.date
                }
                binding.editBtn.setOnClickListener {
                    onEditEventItemClickListener?.invoke(item)
                }
            }
        }
    }

    fun getCurrentItem(position: Int): Event = getItem(position)

    override fun getItemViewType(position: Int): Int {
        val item = getItem(position)
        return when(item.status) {
            VIEW_EXPECTED -> VIEW_EXPECTED_VALUE
            VIEW_MISSED -> VIEW_MISSED_VALUE
            VIEW_VISITED -> VIEW_VISITED_VALUE
            else -> VIEW_UNDEFINED_VALUE
        }
    }

    companion object {

        const val VIEW_EXPECTED = "expected"
        const val VIEW_MISSED = "missed"
        const val VIEW_VISITED = "visited"

        const val VIEW_EXPECTED_VALUE = 0
        const val VIEW_MISSED_VALUE = 1
        const val VIEW_VISITED_VALUE = 2
        const val VIEW_UNDEFINED_VALUE = -1

        const val MAX_POOL_SIZE = 7
    }
}