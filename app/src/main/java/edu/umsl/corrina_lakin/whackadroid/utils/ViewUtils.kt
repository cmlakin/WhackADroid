package edu.umsl.corrina_lakin.whackadroid.utils

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import edu.umsl.corrina_lakin.whackadroid.R
import edu.umsl.corrina_lakin.whackadroid.data.GameMode
import edu.umsl.corrina_lakin.whackadroid.data.Score

object ViewUtils {

    fun showResult(
        context: Context,
        score: Long,
        mode: GameMode,
        onGoHome: () -> Unit,
        onTryAgain: () -> Unit
    ) {

        lateinit var dialog: AlertDialog

        val view = LayoutInflater.from(context)
            .inflate(R.layout.content_results, null, false)

        val userName = view.findViewById<EditText>(R.id.etUserName)
        val userScore = view.findViewById<TextView>(R.id.tvScore)
        userScore.text = score.toString()

        val btnTryAgain = view.findViewById<Button>(R.id.btnTryAgain)
        btnTryAgain.setOnClickListener {
            val storedScore = storeScore(userName, score, mode)  {
                // dismiss dialog
                dialog.dismiss()
                // trigger try again action
                onTryAgain.invoke()
            }
        }

        val btnGoHome = view.findViewById<Button>(R.id.btnGoHome)
        btnGoHome.setOnClickListener {

            val storedScore = storeScore(userName, score, mode) {
                // dismiss dialog
                dialog.dismiss()
                // trigger go home action
                onGoHome.invoke()
            }
        }

        // create dialog with result, view and show dialog
        dialog = AlertDialog.Builder(context)
            .setView(view)
            .setCancelable(false)
            .create()

        dialog.show()
    }

    private fun storeScore(userName: EditText, score: Long, mode: GameMode, callback: () -> Unit): Boolean {
        val name = userName.text.toString()
        val isValidName = name.isNotBlank()

        // check name is valid
        if (isValidName){
            val score = Score(
                username = name,
                score = score,
                mode = mode.name
            )

            DataRepository.addScore(score, callback)
        } else {
            val errorMsg = "Enter User Name"
            Toast.makeText(userName.context, errorMsg, Toast.LENGTH_LONG).show()
        }

        return isValidName
    }

}

