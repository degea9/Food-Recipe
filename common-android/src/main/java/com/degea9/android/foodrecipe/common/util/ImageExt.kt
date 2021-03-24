package com.degea9.android.foodrecipe.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.Target
import timber.log.Timber

@BindingAdapter("rawImageSrc")
fun setRawImageUrl(view: ImageView, path: String?) {
    Timber.d("Image URL: $path")
    try {
        Glide
            .with(view.context)
            .load(path)
            .apply(RequestOptions.overrideOf(Target.SIZE_ORIGINAL))
            .into(view)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}