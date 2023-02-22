package com.anime_clean_sample.presentation.view.bindingadapter

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import com.anime_clean_sample.presentation.view.imgloader.loadImage

@BindingAdapter("imgUrl")
fun AppCompatImageView.imgUrl(url: String?) {
    url?.let {
        loadImage(context, url, this)
    }
}