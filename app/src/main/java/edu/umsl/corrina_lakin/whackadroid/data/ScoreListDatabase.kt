package edu.umsl.corrina_lakin.whackadroid.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(
    entities = [Score::class],
    version = 3,
    exportSchema = false
)

abstract class ScoreListDatabase: RoomDatabase() {

    abstract fun scoreListDao(): ScoresDao

    companion object {
        // name the db file
        private const val DATABASE_FILE = "Score.db"
        // reference to single instance of the db
        private lateinit var instance: ScoreListDatabase

        fun getInstance(context: Context): ScoreListDatabase {
            // try creating database if not initialized
            if(!::instance.isInitialized) {
                // prevent multi-threads access
                synchronized(this) {
                    instance = Room.databaseBuilder(context, ScoreListDatabase::class.java, DATABASE_FILE)
                        .fallbackToDestructiveMigration()
                        .build()
                }
            }
            return instance
        }
    }

}