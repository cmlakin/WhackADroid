package edu.umsl.corrina_lakin.whackadroid.game

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.mvc.interfaces.GameController


class GameAdapter(mode: GameMode, private val controller: GameController) : RecyclerView.Adapter<GameAdapter.ViewHolder>() {
    private val grid: Array<Boolean>
    private val buttonSize: Int
    private var currentItem = -1

    init {
        val gridSize = mode.count * mode.count
        grid = Array(gridSize) { false }

        buttonSize =  when (mode) {
            GameMode.EASY -> R.dimen.easy_button_size
            GameMode.MEDIUM -> R.dimen.medium_button_size
            GameMode.HARD -> R.dimen.hard_button_size
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_button, parent, false)

        val size = parent.context.resources.getDimensionPixelSize(buttonSize)

        val viewParams = view.layoutParams
        viewParams.height = size
        viewParams.width = size
        view.layoutParams = viewParams

        return ViewHolder(view = view)
    }

    override fun getItemCount(): Int = grid.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindTo(grid[position], position)
    }

    fun selectItem(position: Int) {
        if(currentItem == -1) {
            currentItem = position
            // set initial state
            grid[currentItem] = true
            notifyItemChanged(position)
        } else {
            val oldPosition = currentItem
            currentItem = position

            // update state
            grid[oldPosition] = false
            grid[currentItem] = true

            notifyItemChanged(oldPosition)
            notifyItemChanged(currentItem)
        }
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view), View.OnClickListener {
        private val button: ImageView = view.findViewById(R.id.btnView)
        private var gridPosition = -1

        init {
            button.setOnClickListener(this)
        }

        fun bindTo(display: Boolean, position: Int) {
            if (display) button.setImageResource(R.drawable.ic_droid8dp)
            else button.setImageDrawable(null)

            gridPosition = position
        }

        override fun onClick(v: View) {
            controller.checkPosition(gridPosition)
        }
    }

}