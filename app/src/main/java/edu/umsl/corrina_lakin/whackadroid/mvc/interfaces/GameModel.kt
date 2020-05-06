package edu.umsl.corrina_lakin.whackadroid.mvc.interfaces

import edu.umsl.corrina_lakin.whackadroid.data.GameMode

interface GameModel {

    fun initialize(mode: GameMode): Int

    fun checkDroid(position: Int)

    fun getNextDroidPosition(): Int

    fun onScoreChanged(callback: (Int) -> Unit)
}