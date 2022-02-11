package com.enes.moviesapp.ui.homeFragment

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.enes.moviesapp.adapter.viewPagerAdapter.RcViewPager
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentHomeBinding
import com.enes.moviesapp.ui.nowPlayingFragment.NowPLayingFragment
import com.enes.moviesapp.ui.populerFragment.PopularFragment
import com.enes.moviesapp.ui.topRatedFragment.TopRatedMoviesFragment
import com.enes.moviesapp.ui.upComingFragment.UpComingFragment
import com.google.android.material.tabs.TabLayoutMediator
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>() {

    override fun getViewBinding() = FragmentHomeBinding.inflate(layoutInflater)
    override fun setViewpager() {
        val homeFrag = TopRatedMoviesFragment.newInstance()
        val popularFrag = PopularFragment.newInstance()
        val upComingFrag = UpComingFragment.newInstance()
        val nowPlayFrag = NowPLayingFragment.newInstance()

        val fragmentArray: ArrayList<Fragment> = arrayListOf()
        fragmentArray.add(homeFrag)
        fragmentArray.add(popularFrag)
        fragmentArray.add(upComingFrag)
        fragmentArray.add(nowPlayFrag)

        val adapter = RcViewPager(requireActivity(), fragmentArray)

        binding.apply {
            pager.adapter = adapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->

                when (position) {
                    0 -> {
                        tab.text = "Top Rated"
                    }
                    1 -> {
                        tab.text = "popular"
                    }
                    2 -> {
                        tab.text = "UpComing"
                    }
                    3 -> {
                        tab.text = "Now Playing"
                    }
                }

                pager.layoutDirection = ViewPager2.LAYOUT_DIRECTION_LTR
                tabLayout.layoutDirection = View.LAYOUT_DIRECTION_LTR

            }.attach()
        }
    }


    companion object {
        fun newInstance() =
            HomeFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }


}