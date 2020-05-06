package edu.umsl.corrina_lakin.whackadroid.mvc.interfaces

import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.game.GameTimer


interface GameController {

    fun startGame(mode: GameMode, timer: GameTimer)

    fun checkPosition(position: Int)

    fun onScoreChanged(callback: (Int) -> Unit)
}