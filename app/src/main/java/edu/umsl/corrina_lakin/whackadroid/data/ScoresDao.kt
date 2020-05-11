package edu.umsl.corrina_lakin.whackadroid.data

import androidx.room.*

@Dao
interface ScoresDao {
    @Query("SELECT * FROM score WHERE mode = :mode")
    fun getScoresLists(mode: String): List<Score>

    @Insert
    fun addScore(list: Score): Long

    @Delete
    fun deleteScore (list: Score)

}


