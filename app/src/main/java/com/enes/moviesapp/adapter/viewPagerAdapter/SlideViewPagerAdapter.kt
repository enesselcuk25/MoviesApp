package com.enes.moviesapp.adapter.viewPagerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.databinding.ItemPagerAdapterBinding
import kotlin.collections.ArrayList

class SlideViewPagerAdapter(private var list: ArrayList<MoviesList>) :
    PagerAdapter() {


    private var imageViewOnClick: ((MoviesList, View) -> Unit)? = null
    fun onClick(newMoviesList: (MoviesList, View) -> Unit) {
        this.imageViewOnClick = newMoviesList
    }

    override fun getCount(): Int {
        return list.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object`
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val binding = ItemPagerAdapterBinding.inflate(
            LayoutInflater.from(container.context),
            container,
            false
        )

        binding.root.setOnClickListener {
            imageViewOnClick?.invoke(list[position], it)
        }
        binding.pagerMovies = list[position]

        val viewPager = container as ViewPager
        viewPager.addView(binding.root)
        return binding.root
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        val viewPager = container as ViewPager
        val view = `object` as View
        viewPager.removeView(view)
    }

    fun updateMovieList(newMovieList: List<MoviesList>) {
        list.clear()
        list.addAll(newMovieList)
        notifyDataSetChanged()
    }

}