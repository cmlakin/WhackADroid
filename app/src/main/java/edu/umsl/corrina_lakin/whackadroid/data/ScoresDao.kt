package edu.umsl.corrina_lakin.whackadroid.data

import androidx.room.*

@Dao
interface ScoresDao {
    @Query("SELECT * FROM score_list")
    fun getScoresLists(): List<ScoreList>

    @Query("SELECT * FROM score_list WHERE id= :id")
    fun getScoreListById(id: Long): ScoreListInfo

    @Insert
    fun addScoreList (list: ScoreList): Long

    @Update
    fun updateScoreList (list: ScoreList)

    @Delete
    fun deleteScoreList (list: ScoreList)

}


