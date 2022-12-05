package test.mertech.eventplanner.mvvm.presentation.utils.geoLocation

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import java.io.IOException
import java.util.Locale
import javax.inject.Inject

object GeoCoderHandler : Handler() {
    override fun handleMessage(message: Message) {
        val locationAddress: String?
        locationAddress = when (message.what) {
            1 -> {
                val bundle = message.data
                bundle.getString("address")
            }
            else -> null
        }
        Log.d("Location", "$locationAddress")
    }
}