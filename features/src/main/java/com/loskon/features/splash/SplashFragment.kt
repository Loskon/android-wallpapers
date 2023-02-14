package com.loskon.features.splash

import android.os.CountDownTimer
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.loskon.base.countdowntimer.ShortCountDownTimer
import com.loskon.features.R

class SplashFragment : Fragment(R.layout.fragment_splash) {

    private var timer: CountDownTimer? = null

    override fun onStart() {
        super.onStart()

        ShortCountDownTimer(
            SPLASH_DURATION
        ) {
            findNavController().navigate(SplashFragmentDirections.openCategoryListFragment())
        }.start()
    }

    override fun onStop() {
        super.onStop()

        timer?.cancel()
        timer = null
    }

    companion object {
        private const val SPLASH_DURATION = 1000L
    }
}