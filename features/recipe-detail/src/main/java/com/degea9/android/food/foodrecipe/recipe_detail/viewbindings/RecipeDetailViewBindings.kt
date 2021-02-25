package com.degea9.android.food.foodrecipe.recipe_detail.viewbindings

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imageSrc")
fun setImageUrl(view: ImageView, path: String?) {

    try {
        val requestOptions = RequestOptions()

        Glide
            .with(view.context)
            .setDefaultRequestOptions(requestOptions.centerCrop())
            .load(path)
            .into(view)
    } catch (e: Exception) {
        e.printStackTrace()
    }
}