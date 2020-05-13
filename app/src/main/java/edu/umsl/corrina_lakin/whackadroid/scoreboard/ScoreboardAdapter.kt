package edu.umsl.corrina_lakin.whackadroid.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.Score
import edu.umsl.corrina_lakin.whackadroid.utils.DataRepository

class ScoreboardAdapter : RecyclerView.Adapter<ScoreboardAdapter.ViewHolder>() {


    val list: MutableList<Score> = mutableListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(
            R.layout.list_item_user_score,
            parent,
            false
        )
        return ViewHolder(view)
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = list[position]
        holder.bindTo(item)
    }

    fun submitList(score: List<Score>) {
        // update existing list
        list.clear()
        list.addAll(score)
        notifyDataSetChanged()
    }

    inner class ViewHolder(v: View): RecyclerView.ViewHolder(v) {
        private val username = v.findViewById<TextView>(R.id.tvUserName)
        private val userscore = v.findViewById<TextView>(R.id.tvScore)
        //private lateinit var score: Score

        fun bindTo(item: Score){

            username.text = item.username
            userscore.text = item.score.toString()
        }

    }
}