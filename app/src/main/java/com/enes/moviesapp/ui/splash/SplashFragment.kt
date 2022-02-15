package com.enes.moviesapp.ui.splash


import android.os.CountDownTimer
import androidx.navigation.fragment.findNavController
import com.enes.moviesapp.R
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentSplashBinding


class SplashFragment :BaseFragment<FragmentSplashBinding>() {

    override fun getViewBinding() = FragmentSplashBinding.inflate(layoutInflater)

    private lateinit var countDownTimer: CountDownTimer

    override fun splashCreated() {

        binding.animationView.playAnimation()
        countDownTimer = object :CountDownTimer(3000,1000){
            override fun onTick(p0: Long) {}

            override fun onFinish() {
                findNavController().popBackStack()
                findNavController().navigate(R.id.homeFragment)
            }

        }.start()
    }

    override fun onDestroy() {
        super.onDestroy()
        countDownTimer.cancel()
    }
}