package com.example.beerapp.util

import androidx.appcompat.widget.AppCompatImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide

@BindingAdapter("android:src")
fun AppCompatImageView.setImage(url: String?) = url?.let {
    Glide.with(context)
        .load(url.toUri())
        .into(this)
}
