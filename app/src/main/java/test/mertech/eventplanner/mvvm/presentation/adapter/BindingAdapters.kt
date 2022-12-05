package test.mertech.eventplanner.mvvm.presentation.adapter

import androidx.databinding.BindingAdapter
import com.google.android.material.textfield.TextInputLayout
import test.mertech.eventplanner.R

@BindingAdapter("errorInputTitle")
fun bindErrorInputTitle(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.invalid_title)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputDate")
fun bindErrorInputDate(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.invalid_date)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputCity")
fun bindErrorInputCity(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.invalid_city)
    } else {
        null
    }
    textInputLayout.error = message
}

@BindingAdapter("errorInputStreet")
fun bindErrorInputStreet(textInputLayout: TextInputLayout, isError: Boolean) {
    val message = if (isError) {
        textInputLayout.context.getString(R.string.invalid_street)
    } else {
        null
    }
    textInputLayout.error = message
}