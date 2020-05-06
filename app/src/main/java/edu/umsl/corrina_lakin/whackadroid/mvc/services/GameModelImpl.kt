package edu.umsl.corrina_lakin.whackadroid.mvc.services

import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameModel
import java.util.*

class GameModelImpl(private val points: Int) : GameModel {

    private lateinit var grid: Array<Boolean>
    private var currentPosition = -1
    private val ramdom = Random()
    private var scoreChanged: ((Int) -> Unit)? = null

    var score = 0
        private set

    override fun initialize(mode: GameMode): Int {
        val size = mode.count * mode.count
        grid = Array(size) { false }
        return getNextDroidPosition()
    }

    override fun checkDroid(position: Int) {
        if (position == currentPosition) {
            score += points
            scoreChanged?.invoke(score)
        }
    }

    override fun getNextDroidPosition(): Int {
        currentPosition = ramdom.nextInt(grid.size)
        return currentPosition
    }

    override fun onScoreChanged(callback: (Int) -> Unit) {
        scoreChanged = callback
    }

}