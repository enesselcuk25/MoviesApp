package com.enes.moviesapp.util

import android.opengl.Visibility
import android.view.View
import android.widget.ImageView
import android.widget.ProgressBar
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.enes.moviesapp.BuildConfig
import com.enes.moviesapp.data.remote.response.movies.ResultDetails
import com.enes.moviesapp.ui.search.adapter.SearchAdapter

@BindingAdapter("app:visibility")
fun ProgressBar.esShow(visibility: Boolean) {
    this.visibility = if (visibility) View.VISIBLE else View.GONE
}


@BindingAdapter("movieImage")
fun ImageView.setImageUrl(imageUrl: String) {
    Glide.with(context)
        .load(BuildConfig.IMAGE_BASE_W500 + imageUrl)
        .fitCenter()
        .into(this)
}


@BindingAdapter("likedImage")
fun ImageView.setLikedUrl(imageUrl: String) {
    Glide.with(context)
        .load(BuildConfig.IMAGE_BASE_W500 + imageUrl)
        .fitCenter()
        .into(this)
}


@BindingAdapter("movieDetailImage")
fun ImageView.setImageDetailUrl(imageUrl: String?) {
    Glide.with(context)
        .load(BuildConfig.IMAGE_BASE + imageUrl)
        .fitCenter()
        .into(this)
}

@BindingAdapter("app:movieSearchImage")
fun ImageView.setImageSearchUrl(imageUrl: String?) {
    Glide.with(context)
        .load(BuildConfig.IMAGE_BASE + imageUrl)
        .fitCenter()
        .apply(RequestOptions.circleCropTransform())
        .into(this)
}



