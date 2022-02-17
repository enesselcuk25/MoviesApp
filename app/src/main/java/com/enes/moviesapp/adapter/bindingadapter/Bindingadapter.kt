package com.enes.moviesapp.adapter.bindingadapter


import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.enes.moviesapp.BuildConfig

object ImageBindingAdapter {
    @JvmStatic
    @BindingAdapter("movieImage")
    fun ImageView.setImageUrl(imageUrl: String) {
        Glide.with(context)
            .load(BuildConfig.IMAGE_BASE_W500 + imageUrl)
            .fitCenter()
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("likedImage")
    fun ImageView.setLikedUrl(imageUrl: String) {
        Glide.with(context)
            .load(BuildConfig.IMAGE_BASE_W500 + imageUrl)
            .fitCenter()
            .into(this)
    }

    @JvmStatic
    @BindingAdapter("movieDetailImage")
    fun ImageView.setImageDetailUrl(imageUrl: String?) {
        Glide.with(context)
            .load(BuildConfig.IMAGE_BASE + imageUrl)
            .fitCenter()
            .into(this)
    }
}