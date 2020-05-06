package edu.umsl.corrina_lakin.whackadroid.utils

import android.content.Context
import android.os.Handler
import android.os.Looper
import edu.umsl.corrina_lakin.whackadroid.data.ScoreList
import edu.umsl.corrina_lakin.whackadroid.data.ScoreListDatabase
import edu.umsl.corrina_lakin.whackadroid.data.ScoreListInfo
import java.util.concurrent.Executors

object DataRepository {

    private val handler by lazy { Handler(Looper.getMainLooper()) }
    private val executor by lazy { Executors.newSingleThreadExecutor()}
    private lateinit var database: ScoreListDatabase

    fun createDatabase(context: Context) {
        database = ScoreListDatabase.getInstance(context.applicationContext)
    }

    fun getScoreLists(callback: (List<ScoreList>) -> Unit) {
        executor.execute {
            val list = database.ScoreListDao().getScoresLists()
            handler.post { callback.invoke(list)}
        }
    }

    fun getScoreListsById(id: Long, callback: (ScoreListInfo) -> Unit) {
        executor.execute {
            val list = database.ScoreListDao().getScoreListById(id)
            handler.post { callback.invoke(list) }
        }
    }

    fun addScoreList(ScoreList:ScoreList, callback: (ScoreList) -> Unit){
        executor.execute {
            val id = database.ScoreListDao().addScoreList(ScoreList)
            handler.post { callback.invoke(ScoreList)}
        }
    }

    fun deleteScoreList(scoreListToDelete: ScoreList, callback: () -> Unit){
        executor.execute {
            val id = database.ScoreListDao().deleteScoreList(scoreListToDelete)
            handler.post { callback.invoke()}
        }
    }

    fun updateScoreList (itemToUpdate: ScoreList, callback: () -> Unit) {
        executor.execute {
            database.ScoreListDao().updateScoreList(itemToUpdate)
            handler.post { callback.invoke() }
        }
    }
}


