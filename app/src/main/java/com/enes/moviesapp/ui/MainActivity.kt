package com.enes.moviesapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import androidx.navigation.ui.setupWithNavController
import com.enes.moviesapp.R
import com.enes.moviesapp.databinding.ActivityMainBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // setPager()
        setBottomNav()
    }

    private fun setBottomNav() {
        val navController = findNavController(R.id.fragment)

        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.homeFragment,R.id.likedFragment
                )
        )

        setupActionBarWithNavController(navController, appBarConfiguration)
        binding.navView.setupWithNavController(navController)
    }

    /*
    private fun setPager(){
        val homeFrag = HomeMoviesFragment.newInstance()
        val popularFrag = PopularFragment.newInstance()
        val upComingFrag = UpComingFragment.newInstance()
        val nowPlayFrag = NowPLayingFragment.newInstance()

        val fragmentArray :ArrayList<Fragment> = arrayListOf()
        fragmentArray.add(homeFrag)
        fragmentArray.add(popularFrag)
        fragmentArray.add(upComingFrag)
        fragmentArray.add(nowPlayFrag)

       val adapter =  RcViewPager(this,fragmentArray)

        binding.apply {
            pager.adapter = adapter
            TabLayoutMediator(tabLayout, pager) { tab, position ->

                when(position){
                    0 -> {
                        tab.text = "Top Rated "
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

     */

}