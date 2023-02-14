package com.loskon.base.countdowntimer

import android.os.CountDownTimer

open class ShortCountDownTimer(
    millis: Long,
    val onFinishTimer: () -> Unit
) : CountDownTimer(millis, millis) {

    @Suppress("EmptyFunctionBlock")
    override fun onTick(p0: Long) {}
    override fun onFinish() = onFinishTimer()
}