package edu.umsl.corrina_lakin.whackadroid.game

import android.os.Handler
import android.os.Looper
import android.widget.TextView
import java.util.*
import kotlin.concurrent.timerTask

typealias TickListener = (Long) -> Unit


class GameTimer(private val textView: TextView, private var duration: Long) {
    private val uiHandler = Handler(Looper.getMainLooper())
    private val timer = Timer("Game duration")
    private val tickListeners = mutableListOf<TickListener>()

    val currentTime: String get() {
        // format time
        val min = String.format("%02d", duration / 60)
        val sec = String.format("%02d", duration % 60)
        val time = "$min:$sec"
        return time
    }

    fun startTimer(onFinish: () -> Unit) {
        // function to handle timer tick
        val updateTask = timerTask {
            // reduce time
            duration -= 1

            val hasRemainingTime = duration >= 0
            if (hasRemainingTime) {
                // update text view on ui thread
                uiHandler.post {
                    textView.text = currentTime
                    tickListeners.forEach { it.invoke(duration) }
                }
            } else {
                // cancel timer update
                timer.cancel()
                // trigger time finished
                onFinish.invoke()
            }
        }

        // schedule task to trigger update every half second
        timer.schedule(updateTask, 0, 500L)
    }

    fun onTick(callback: TickListener) {
        tickListeners.add(callback)
    }
}