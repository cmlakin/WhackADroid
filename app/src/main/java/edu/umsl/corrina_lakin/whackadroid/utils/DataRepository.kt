package edu.umsl.corrina_lakin.whackadroid.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.data.Score
import edu.umsl.corrina_lakin.whackadroid.data.ScoreListDatabase
import java.util.concurrent.Executors

object DataRepository {

    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private val executor by lazy { Executors.newSingleThreadExecutor() }
    private lateinit var database: ScoreListDatabase

    fun initiailize(context: Context) {
        database = ScoreListDatabase.getInstance(context.applicationContext)
    }

    fun getScoresLists(mode: GameMode, callback: (List<Score>) -> Unit) {
        executor.execute {
            val list = database.scoreListDao().getScoresLists(mode.name)
            handler.post { callback.invoke(list) }
        }
    }

    fun addScore(score: Score, callback: () -> Unit) {
        executor.execute {
            val id =database.scoreListDao().addScore(score)
            handler.post { callback.invoke() }
        }
    }

    fun deleteScore(scoreToDelete: Score, callback: () -> Unit) {
        executor.execute {
            val id = database.scoreListDao().deleteScore(scoreToDelete)
            handler.post { callback.invoke() }
        }
    }

}


