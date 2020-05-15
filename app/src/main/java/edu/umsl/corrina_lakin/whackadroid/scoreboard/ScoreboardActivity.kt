package edu.umsl.corrina_lakin.whackadroid.scoreboard

import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
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

        val list = GameMode.values().map { it.name }
        scoreFilter.adapter = ArrayAdapter(this, R.layout.list_item_score_spinner, list)
        scoreFilter.onItemSelectedListener = this
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        } else {
            return super.onOptionsItemSelected(item)
        }
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