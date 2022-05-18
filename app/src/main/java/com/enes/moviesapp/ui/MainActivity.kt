package com.enes.moviesapp.ui

import android.annotation.SuppressLint
import android.content.res.ColorStateList
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
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
        mainActivity = this
        setBottomNav()
    }

    @SuppressLint("ResourceAsColor")
    private fun setBottomNav() {
        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.fragment) as NavHostFragment
        val navController = navHostFragment.navController
        binding.navView.setupWithNavController(navController)

        binding.navView.itemRippleColor =
            ColorStateList.valueOf(R.color.white)
        binding.navView.itemIconTintList = null

    }

    companion object {
        lateinit var mainActivity: MainActivity
    }

//    fun show(visibility:Boolean){
//        binding.progresBar.visibility = if(visibility) View.VISIBLE else View.GONE
//    }
}