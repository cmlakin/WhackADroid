package edu.umsl.corrina_lakin.whackadroid.scoreboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.ScoreList

class ScoreboardAdapter(private val scores: List<ScoreList>)  {

//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserScoreViewHolder {
//        val view = LayoutInflater.from(parent.context).inflate(R.layout.list_item_user_score, parent, false)
//        return UserScoreViewHolder(view)
//    }
//
//    override fun getItemCount(): Int {
//        return scores.size
//    }
//
//    override fun onBindViewholder(holder: UserScoreViewHolder, position: Int) {
//        //TODO implement
//    }

    inner class UserScoreViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val username = view.findViewById<TextView>(R.id.tvUserName)
        private val userscore = view.findViewById<TextView>(R.id.tvScore)
        private val misses = view.findViewById<TextView>(R.id.tvMisses)

        //TODO implement binding
//        fun bindTo()
    }
}