package test.mertech.eventplanner.mvvm.presentation.utils

import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import coil.ImageLoader
import coil.decode.SvgDecoder
import coil.request.ImageRequest

fun ImageView.loadSvg(imageUrl: String) {
    val imageLoader = ImageLoader.Builder(this.context)
        .componentRegistry {
            add(SvgDecoder(this@loadSvg.context))
        }
        .build()

    val imageRequest = ImageRequest.Builder(this.context)
        .crossfade(true)
        .crossfade(300)
        .data(imageUrl)
        .target(
            onStart = {
                //set up an image loader or whatever you need
            },
            onSuccess = { result ->
                val bitmap = (result as BitmapDrawable).bitmap
                this.setImageBitmap(bitmap)
                //dismiss the loader if any
            },
            onError = {
                /**
                 * TODO: set an error drawable
                 */
            }
        )
        .build()

    imageLoader.enqueue(imageRequest)
}