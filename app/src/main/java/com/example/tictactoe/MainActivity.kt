package com.example.tictactoe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {
    lateinit var board: Array<Array<Button>>
    var PLAYER = true
    var turnCount = 0
    var boardStatus = Array(3,{IntArray(3)})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        board = arrayOf(
            arrayOf(btn1,btn2,btn3),
            arrayOf(btn4,btn5,btn6),
            arrayOf(btn7,btn8,btn9)
        )

        for(i in board){
            for(btn in i){
                btn.setOnClickListener(this)
            }
        }

        initializeBoardStatus();

        btnReset.setOnClickListener{
            PLAYER = true
            turnCount = 0
            updateDisplay("Player X turn")
            initializeBoardStatus()
        }
        
    }

    private fun initializeBoardStatus() {
        for(i in 0..2){
            for(j in 0..2){
                boardStatus[i][j] = -1
                board[i][j].isEnabled = true
                board[i][j].text = ""
            }
        }
    }

    override fun onClick(view: View?) {
        when(view?.id){
            R.id.btn1->{
                updateValue(row = 0, column = 0, player=PLAYER)

            }
            R.id.btn2->{
                updateValue(row = 0, column = 1, player=PLAYER)
            }
            R.id.btn3->{
                updateValue(row = 0, column = 2, player=PLAYER)
            }
            R.id.btn4->{
                updateValue(row = 1, column = 0, player=PLAYER)
            }
            R.id.btn5->{
                updateValue(row = 1, column = 1, player=PLAYER)
            }
            R.id.btn6->{
                updateValue(row = 1, column = 2, player=PLAYER)
            }
            R.id.btn7->{
                updateValue(row = 2, column = 0, player=PLAYER)
            }
            R.id.btn8->{
                updateValue(row = 2, column = 1, player=PLAYER)
            }
            R.id.btn9->{
                updateValue(row = 2, column = 2, player=PLAYER)
            }
        }

        PLAYER = !PLAYER
        turnCount++

        if(PLAYER){
            updateDisplay("Player X turn")
        }
        else{
            updateDisplay("Player O turn")
        }

        checkWinner()

        if(turnCount==9){
            updateDisplay("Game Draw")
        }
    }

    private fun checkWinner() {
        //Horizontal rows
        for(i in 0..2){
            if(boardStatus[i][0] == boardStatus[i][1] && boardStatus[i][1] == boardStatus[i][2]){
                if(boardStatus[i][0] == 1){
                    updateDisplay("Player X Wins!!")
                    break
                }
                else if(boardStatus[i][0] == 0){
                    updateDisplay("Player O Wins!!")
                    break
                }
            }
        }

        //Vertical rows
        for(i in 0..2){
            if(boardStatus[0][i] == boardStatus[1][i] && boardStatus[1][i] == boardStatus[2][i]){
                if(boardStatus[0][i] == 1){
                    updateDisplay("Player X Wins!!")
                    break
                }
                else if(boardStatus[0][i] == 0){
                    updateDisplay("Player O Wins!!")
                    break
                }
            }
        }

        //Left diagonal
        if(boardStatus[0][0] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][2]){
            if(boardStatus[0][0] == 1){
                updateDisplay("Player X Wins!!")
            }
            else if(boardStatus[0][0] == 0){
                updateDisplay("Player O Wins!!")
            }
        }

        //Right diagonal
        if(boardStatus[0][2] == boardStatus[1][1] && boardStatus[1][1] == boardStatus[2][0]){
            if(boardStatus[0][2] == 1){
                updateDisplay("Player X Wins!!")
            }
            else if(boardStatus[0][2] == 0){
                updateDisplay("Player O Wins!!")
            }
        }
    }

    private fun disableButtons() {
        for(i in board){
            for(btn in i){
                btn.isEnabled = false
            }
        }
    }

    private fun updateDisplay(s: String) {
        tvDisplay.text = s
        if(s.contains("Wins")){
            disableButtons()
        }
    }

    private fun updateValue(row: Int, column: Int, player: Boolean) {

        val value = if(player)"X" else "O"
        val v = if(player) 1 else 0
            board[row][column].apply{
                isEnabled = false
                setText(value)
            }
        boardStatus[row][column] = v

    }
}