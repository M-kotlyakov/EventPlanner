package test.mertech.eventplanner.mvvm.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import test.mertech.eventplanner.mvvm.domain.entity.Event

class EventDiffCallback: DiffUtil.ItemCallback<Event>() {

    override fun areItemsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Event, newItem: Event): Boolean {
        return oldItem == newItem
    }
}