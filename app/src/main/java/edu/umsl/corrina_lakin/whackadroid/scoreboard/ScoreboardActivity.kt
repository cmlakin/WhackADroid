package edu.umsl.corrina_lakin.whackadroid.scoreboard

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import edu.umsl.corrina_lakin.whackadroid.R

class ScoreboardActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_scoreboard)

        // back arrow on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        // TODO setup view holder

    }

    //TODO fucntion for getting scorelist
}