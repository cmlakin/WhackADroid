package edu.umsl.corrina_lakin.whackadroid.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameController
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameView
import edu.umsl.corrina_lakin.whackadroid.mvc.services.GameControllerImpl
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity: AppCompatActivity(), GameView  {
    private lateinit var adapter: GameAdapter
    private lateinit var controller: GameController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val gameMode = intent.getStringExtra(KEY_GAME_MODE) ?: GameMode.EASY.name
        controller = GameControllerImpl(this, 10)
        setupUI(gameMode)
    }

    private fun setupUI(gameMode: String) {
        // set timer to 2 mins
        val timer = GameTimer(gameTimer,  30)

        // set text for current time
        gameTimer.text = timer.currentTime

        val mode = GameMode.valueOf(gameMode)

        // setup start button
        startBtn.setOnClickListener {
            controller.startGame(mode, timer)
        }

        // listen for score chnage
        controller.onScoreChanged {
            score.text = "$it"
        }

        // setup recycler view
        adapter = GameAdapter(mode, controller)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, mode.count)
    }

    override fun showResult() {

    }

    override fun nextPosition(position: Int) {
        adapter.selectItem(position)
    }


    companion object {
        const val KEY_GAME_MODE = "game_mode_key"

    }
}