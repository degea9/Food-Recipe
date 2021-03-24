package com.degea9.android.foodrecipe.viewbindings

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

@BindingAdapter("imageState")
fun setImageState(imageView :ImageView,isFavorite:Boolean?){
    isFavorite?.let {
        val stateSet =
            intArrayOf(android.R.attr.state_checked * if (isFavorite) 1 else -1)
        imageView.setImageState(stateSet, true)
    }
}
