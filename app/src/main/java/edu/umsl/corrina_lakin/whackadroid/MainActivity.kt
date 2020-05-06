package edu.umsl.corrina_lakin.whackadroid

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.View.OnClickListener
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.game.GameActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), OnClickListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // attach click listeners
        tv_easy.setOnClickListener(this)
        tv_medium.setOnClickListener(this)
        tv_hard.setOnClickListener(this)

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_main, menu)
        return true
    }
    //TODO: create ScoreBoard activity
//    override fun onOptionsItemSelected(item: MenuItem): Boolean {
//        return when (item.itemId) {
//            R.id.action_scoreboard -> showScoreBoard()
//        }
//    }

//    private fun showScoreboard(): Boolean {
//        // show scoreboard
//        val scoreBoardIntent = Intent(this, ScoreboardActivity::class.java)
//        startActivity(scoreBoardIntent)
//        return true
//    }

    override fun onClick(v: View) {
        val mode = when (v.id) {
            R.id.tv_easy -> GameMode.EASY
            R.id.tv_medium -> GameMode.MEDIUM
            R.id.tv_hard -> GameMode.HARD
            else -> return
        }

        val intent = Intent(this, GameActivity::class.java)
            .putExtra(GameActivity.KEY_GAME_MODE, mode.name)
        startActivity(intent)
    }
}
