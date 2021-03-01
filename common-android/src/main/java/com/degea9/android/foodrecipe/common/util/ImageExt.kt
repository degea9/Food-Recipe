package com.degea9.android.foodrecipe.common.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("rawImageSrc")
fun setRawImageUrl(view: ImageView, path: String?) {
    try {
        Glide
            .with(view.context)
            .load(path)
            .into(view)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}