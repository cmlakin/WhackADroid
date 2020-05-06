package edu.umsl.corrina_lakin.whackadroid.mvc.services

import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.game.GameTimer
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameController
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameModel
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameView

class GameControllerImpl(private val view: GameView, points: Int): GameController {
    private var timeToUpdate = -1L
    private val model: GameModel = GameModelImpl(points)
    private var gameOver = false

    override fun startGame(mode: GameMode, timer: GameTimer) {
        val firstPosition = model.initialize(mode)
        timer.onTick(this::onTick)
        view.nextPosition(firstPosition)
        timer.startTimer {
            gameOver = true
            view.showResult()
        }
    }

    private fun onTick(timeLeft: Long) {
        if (gameOver) return

        if (timeToUpdate < 0) {
            timeToUpdate = timeLeft - 1L
        }

        if (timeToUpdate > timeLeft) {
            timeToUpdate = timeLeft - 1L
            moveToNext()
        }
    }

    override fun checkPosition(position: Int) {
        if (gameOver) return

        model.checkDroid(position)
        timeToUpdate -= 1L
        moveToNext()
    }

    private fun moveToNext() {
        val nextPosition = model.getNextDroidPosition()
        view.nextPosition(nextPosition)
    }

    override fun onScoreChanged(callback: (Int) -> Unit) {
        model.onScoreChanged(callback)
    }

}