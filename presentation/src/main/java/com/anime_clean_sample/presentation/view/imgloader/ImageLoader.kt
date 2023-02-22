package com.anime_clean_sample.presentation.view.imgloader

import android.content.Context
import androidx.appcompat.widget.AppCompatImageView
import com.bumptech.glide.Glide

fun loadImage(ctx: Context, url: String, img: AppCompatImageView) {
    Glide.with(ctx)
        .load(url)
        .optionalFitCenter()
        .into(img)
}