package edu.umsl.corrina_lakin.whackadroid.game

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.GridLayoutManager
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameController
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameView
import edu.umsl.corrina_lakin.whackadroid.mvc.services.GameControllerImpl
import edu.umsl.corrina_lakin.whackadroid.utils.ViewUtils
import kotlinx.android.synthetic.main.activity_game.*

class GameActivity: AppCompatActivity(), GameView  {
    private lateinit var adapter: GameAdapter
    private lateinit var controller: GameController
    private lateinit var mode: GameMode
    private var hasStarted = false
    private var hasEnded = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)
        val gameMode = intent.getStringExtra(KEY_GAME_MODE) ?: GameMode.EASY.name
        mode = GameMode.valueOf(gameMode)

        setupUI()
    }

    private fun setupUI() {
        // create controller
        controller = GameControllerImpl(this, 10)

        // set timer to 2 mins
        val timer = GameTimer(gameTimer,  10)

        // set text for current time
        gameTimer.text = timer.currentTime

        // setup start button
        startBtn.setOnClickListener {
            startBtn.isClickable = false
            startBtn.isEnabled = false

            hasStarted = true
            controller.startGame(mode, timer)
        }

        // listen for score chnage
        controller.onScoreChanged {
            score.text = "$it"
        }

        // setup recycler view
        adapter = GameAdapter(mode, this::onWackDroid)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = GridLayoutManager(this, mode.count)
    }

    override fun showResult() = runOnUiThread {
        hasEnded = true
        val score = score.text.toString().toLong()
        ViewUtils.showResult(this, score, mode, this::finish, this::setupUI)
    }

    override fun nextPosition(position: Int) {
        adapter.selectItem(position)
    }

    private fun onWackDroid(position: Int) {
        if (!hasStarted || hasEnded) return
        controller.checkPosition(position)
    }

    companion object {
        const val KEY_GAME_MODE = "game_mode_key"

    }
}