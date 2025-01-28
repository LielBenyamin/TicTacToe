package com.example.tictactoe

import android.graphics.Color
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private lateinit var button10: Button
    private lateinit var textView: TextView
    private lateinit var textView2: TextView

    private var player1Count = 0
    private var player2Count = 0

    private var playerTurn = true 
    private var activeUser = 1
    private val player1 = ArrayList<Int>()
    private val player2 = ArrayList<Int>()
    private val emptyCells = ArrayList<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button10 = findViewById(R.id.button10)
        textView = findViewById(R.id.textView)
        textView2 = findViewById(R.id.textView2)

        // הגדרת כפתור reset
        button10.setOnClickListener {
            restart()
        }
    }

    
    fun clickfun(view: View) {
        if (playerTurn) {
            val button = view as Button
            val cellID = when (button.id) {
                R.id.button -> 1
                R.id.button2 -> 2
                R.id.button3 -> 3
                R.id.button4 -> 4
                R.id.button5 -> 5
                R.id.button6 -> 6
                R.id.button7 -> 7
                R.id.button8 -> 8
                R.id.button9 -> 9
                else -> return
            }
            playerTurn = false
            Handler().postDelayed({ playerTurn = true }, 600)
            playnow(button, cellID)
        }
    }

   
    fun playnow(buttonSelected: Button, currCell: Int) {

        if (activeUser == 1) {
            buttonSelected.text = "X"
            buttonSelected.setTextColor(Color.parseColor("#EC0C0C"))
            player1.add(currCell)
            emptyCells.add(currCell)

            buttonSelected.isEnabled = false

            if (checkWinner() == 1 || checkDraw()) {
                Handler().postDelayed({ reset() }, 2000)
                return
            } else {
                activeUser = 2
            }
        } else {
            buttonSelected.text = "O"
            buttonSelected.setTextColor(Color.parseColor("#D22BB804"))
            player2.add(currCell)
            emptyCells.add(currCell)

            buttonSelected.isEnabled = false

            if (checkWinner() == 1 || checkDraw()) {
                Handler().postDelayed({ reset() }, 2000)
                return
            } else {
                activeUser = 1
            }
        }
    }

 
    private fun updateScore() {
        textView.text = "Player 1 : $player1Count"
        textView2.text = "Player 2 : $player2Count"
    }

    
    private fun checkWinner(): Int {
        val winningPositions = arrayOf(
            arrayOf(1, 2, 3), // שורה ראשונה
            arrayOf(4, 5, 6), // שורה שנייה
            arrayOf(7, 8, 9), // שורה שלישית
            arrayOf(1, 4, 7), // עמודה ראשונה
            arrayOf(2, 5, 8), // עמודה שנייה
            arrayOf(3, 6, 9), // עמודה שלישית
            arrayOf(1, 5, 9), // אלכסון ראשי
            arrayOf(3, 5, 7)  // אלכסון משני
        )

        for (position in winningPositions) {
            if (player1.containsAll(position.toList())) {
                player1Count++
                updateScore()
                showMessage("Player 1 Wins!")
                return 1
            } else if (player2.containsAll(position.toList())) {
                player2Count++
                updateScore()
                showMessage("Player 2 Wins!")
                return 1
            }
        }

        return 0
    }

   
    private fun checkDraw(): Boolean {
        return if (emptyCells.size == 9) {
            showMessage("It's a Draw!")
            true
        } else {
            false
        }
    }

    private fun showMessage(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }

    fun reset() {
        player1.clear()
        player2.clear()
        emptyCells.clear()
        activeUser = 1
        for (i in 1..9) {
            val buttonSelected: Button = when (i) {
                1 -> findViewById(R.id.button)
                2 -> findViewById(R.id.button2)
                3 -> findViewById(R.id.button3)
                4 -> findViewById(R.id.button4)
                5 -> findViewById(R.id.button5)
                6 -> findViewById(R.id.button6)
                7 -> findViewById(R.id.button7)
                8 -> findViewById(R.id.button8)
                9 -> findViewById(R.id.button9)
                else -> findViewById(R.id.button)
            }
            buttonSelected.isEnabled = true
            buttonSelected.text = ""
        }
        updateScore()
    }

    fun restart() {
        player1.clear()
        player2.clear()

        player1Count = 0
        player2Count = 0

        emptyCells.clear()
        activeUser = 1
        for (i in 1..9) {
            val buttonSelected: Button = when (i) {
                1 -> findViewById(R.id.button)
                2 -> findViewById(R.id.button2)
                3 -> findViewById(R.id.button3)
                4 -> findViewById(R.id.button4)
                5 -> findViewById(R.id.button5)
                6 -> findViewById(R.id.button6)
                7 -> findViewById(R.id.button7)
                8 -> findViewById(R.id.button8)
                9 -> findViewById(R.id.button9)
                else -> findViewById(R.id.button)
            }
            buttonSelected.isEnabled = true
            buttonSelected.text = ""
        }
        updateScore()
    }
}
