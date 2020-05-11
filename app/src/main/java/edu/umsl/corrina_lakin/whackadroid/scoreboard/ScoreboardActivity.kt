package edu.umsl.corrina_lakin.whackadroid.scoreboard

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.appcompat.app.AppCompatActivity
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.data.Score
import edu.umsl.corrina_lakin.whackadroid.utils.DataRepository
import kotlinx.android.synthetic.main.avtivity_scoreboard.*

class ScoreboardActivity : AppCompatActivity(), AdapterView.OnItemSelectedListener {

    private val repository = DataRepository
    private lateinit var adapter: ScoreboardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.avtivity_scoreboard)
        title = "Scoreboard: "

        // back arrow on toolbar
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = ScoreboardAdapter()
        rvUserScores.adapter = adapter

        //getScoresLists()

        scoreFilter.onItemSelectedListener = this
    }


    private fun getScoresLists(gameMode: GameMode = GameMode.EASY) {
        repository.getScoresLists(gameMode) {
            adapter.submitList(it)
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {}

    override fun onItemSelected(parent: AdapterView<*>, view: View?, position: Int, id: Long) {
        // get selected score type
        val selectedScoreType = parent.getItemAtPosition(position) as String
        // get the game mode from score type
        val gameMode = GameMode.valueOf(selectedScoreType.toUpperCase())
        // update list based on selected game mode
        getScoresLists(gameMode)
    }
}