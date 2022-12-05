package test.mertech.eventplanner.mvvm.presentation.utils.geoLocation

import android.content.Context
import android.location.Address
import android.location.Geocoder
import android.os.Bundle
import android.os.Handler
import android.os.Message
import android.util.Log
import okio.IOException
import test.mertech.eventplanner.mvvm.presentation.screens.eventScreen.EventViewModel.Companion.ADDRESS
import java.util.Locale

fun getAddressFromLocation(
    locationAddress: String,
    context: Context,
    handler: Handler
): String {
    val geoCoder = Geocoder(
        context,
        Locale.getDefault()
    )
    var result: String? = null
    try {
        val addressList = geoCoder.getFromLocationName(locationAddress, 1)
        if (addressList != null && addressList.size > 0) {
            val address = addressList[0] as Address
            val sb = StringBuilder()
            sb.append(address.latitude).append(" ")
            sb.append(address.longitude).append("")
            result = sb.toString()
        } else {
            throw RuntimeException("Error inside geoCoder!!! $locationAddress")
        }
    } catch (e: IOException) {
        Log.e("GeoCodeLocation", "Unable to connect to GeoCoder", e)
    } finally {
        val message = Message.obtain()
        message.target = handler
        message.what = 1
        val bundle = Bundle()
        bundle.putString(ADDRESS, result)
        message.data = bundle
        message.sendToTarget()
        Log.d("EventViewModel", "result: $result")
    }
    return result!!
}