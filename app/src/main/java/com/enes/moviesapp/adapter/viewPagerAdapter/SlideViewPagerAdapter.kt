package com.enes.moviesapp.adapter.viewPagerAdapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.viewpager.widget.PagerAdapter
import androidx.viewpager.widget.ViewPager
import com.enes.moviesapp.data.remote.model.MoviesList
import com.enes.moviesapp.databinding.ItemPagerAdapterBinding
import java.util.*
import kotlin.collections.ArrayList

class SlideViewPagerAdapter(private var list: ArrayList<MoviesList>) :
    PagerAdapter(),Filterable {

    var moviesArrayList = ArrayList<MoviesList>()

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

        val positionList = list[position]

        binding.root.setOnClickListener {
            imageViewOnClick?.invoke(positionList, it)
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

    override fun getFilter(): Filter {
        return object :Filter() {
            override fun performFiltering(p0: CharSequence?): FilterResults {
                val charSearch = p0.toString()
                list = if(charSearch.isNotEmpty()){
                    moviesArrayList
                }else{
                    val resultList = ArrayList<MoviesList>()
                    for (row in list){
                        if(row.title.lowercase(Locale.ROOT).contains(charSearch.lowercase(
                                Locale.ROOT))){
                            resultList.add(row)
                        }
                    }
                    resultList
                }
                val filterResults = FilterResults()
                filterResults.values = list
                return filterResults

            }

            override fun publishResults(p0: CharSequence?, p1: FilterResults?) {
                list = p1?.values as ArrayList<MoviesList>
                notifyDataSetChanged()
            }

        }
    }

}