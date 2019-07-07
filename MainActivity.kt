
import android.app.AlertDialog
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random

class MainActivity : AppCompatActivity() {

    var player1 = ArrayList<Int>()
    var player2 = ArrayList<Int>()
    var activePlayer = 1
    var winner = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    protected fun btnClick(view:View){
        val btnSelected = view as Button
        var btnId = 0
        when(btnSelected.id){
            R.id.btn1 -> btnId=1
            R.id.btn2 -> btnId=2
            R.id.btn3 -> btnId=3
            R.id.btn4 -> btnId=4
            R.id.btn5 -> btnId=5
            R.id.btn6 -> btnId=6
            R.id.btn7 -> btnId=7
            R.id.btn8 -> btnId=8
            R.id.btn9 -> btnId=9
        }

        gamePlay(btnId,btnSelected)
    }

    private fun gamePlay(btnId: Int, btnSelected: Button) {

        if(activePlayer == 1){
            btnSelected.setText("x")
            btnSelected.setBackgroundColor(Color.GRAY)
            player1.add(btnId)
            activePlayer = 2
        }else{
            btnSelected.setText("o")
            btnSelected.setBackgroundColor(Color.DKGRAY)
            player2.add(btnId)
            activePlayer = 1
        }
        btnSelected.isEnabled = false

        checkWinner()
        if(winner == -1 && activePlayer==2) {
            autoPlay()
        }
    }

    private fun autoPlay() {

        var emptyCells = ArrayList<Int>()

        for(cellId in 1..9){
            if(!(player1.contains(cellId) || player2.contains(cellId))){
                emptyCells.add(cellId)
            }
        }
        if(!emptyCells.isEmpty()) {
            val r = Random()
            val randomIndex = r.nextInt(emptyCells.size - 0) + 0
            val cellId = emptyCells[randomIndex]

            var btnSelected: Button?

            when (cellId) {
                1 -> btnSelected = btn1
                2 -> btnSelected = btn2
                3 -> btnSelected = btn3
                4 -> btnSelected = btn4
                5 -> btnSelected = btn5
                6 -> btnSelected = btn6
                7 -> btnSelected = btn7
                8 -> btnSelected = btn8
                9 -> btnSelected = btn9
                else -> {
                    btnSelected = btn1
                }
            }
            emptyCells.clear()
            gamePlay(cellId, btnSelected)
        }else{
            winner = 0
            checkWinner()
        }

    }

    fun checkWinner(){

        //player1
        if ((player1.contains(1) && player1.contains(2) && player1.contains(3)) ||
            (player1.contains(4) && player1.contains(5) && player1.contains(6)) ||      //row
            (player1.contains(7) && player1.contains(8) && player1.contains(9))){
            winner = 1
        }else if ((player1.contains(1) && player1.contains(4) && player1.contains(7)) ||
            (player1.contains(2) && player1.contains(5) && player1.contains(8)) ||      //column
            (player1.contains(3) && player1.contains(6) && player1.contains(9))){
            winner = 1
        }else if ((player1.contains(1) && player1.contains(5) && player1.contains(9)) ||       //diagonal
            (player1.contains(3) && player1.contains(5) && player1.contains(7))){
            winner = 1
        }

        //player2
        if ((player2.contains(1) && player2.contains(2) && player2.contains(3)) ||
            (player2.contains(4) && player2.contains(5) && player2.contains(6)) ||      //row
            (player2.contains(7) && player2.contains(8) && player2.contains(9))){
            winner = 2
        }else if ((player2.contains(1) && player2.contains(4) && player2.contains(7)) ||
            (player2.contains(2) && player2.contains(5) && player2.contains(8)) ||      //column
            (player2.contains(3) && player2.contains(6) && player2.contains(9))){
            winner = 2
        }else if ((player2.contains(1) && player2.contains(5) && player2.contains(9)) ||       //diagonal
            (player2.contains(3) && player2.contains(5) && player2.contains(7))){
            winner = 2
        }

        if(winner != -1) {
            gameResult(winner)
        }else if (winner == 0){
            gameResult(winner)
        }
    }

    private fun gameResult(winner: Int) {

        val builder = AlertDialog.Builder(this)
        val title:String?
        val message:String?

        if(winner == 0) {
            title = "Draw"
            message = "Game ended with no result"
        }else if(winner == 1){
            title = "congratulations!"
            message = "You have won"
        }else {
            title = "Sorry!"
            message = "You've lost the game"
        }

        builder.setTitle(title)
        builder.setMessage(message)
        builder.setPositiveButton("Play Again"){dialog, which ->
            newGame()
        }

        builder.setNegativeButton("No, Exit"){dialog, which ->
            moveTaskToBack(true)
            android.os.Process.killProcess(android.os.Process.myPid())
            System.exit(1)
        }

        val alertDialog: AlertDialog = builder.create()
        alertDialog.show()

    }

    private fun newGame() {
        btn1.background = getDrawable(R.color.white)
        btn1.isEnabled = true
        btn1.text =""

        btn2.background = getDrawable(R.color.white)
        btn2.isEnabled = true
        btn2.text =""

        btn3.background = getDrawable(R.color.white)
        btn3.isEnabled = true
        btn3.text =""

        btn4.background = getDrawable(R.color.white)
        btn4.isEnabled = true
        btn4.text =""

        btn5.background = getDrawable(R.color.white)
        btn5.isEnabled = true
        btn5.text =""

        btn6.background = getDrawable(R.color.white)
        btn6.isEnabled = true
        btn6.text =""

        btn7.background = getDrawable(R.color.white)
        btn7.isEnabled = true
        btn7.text =""

        btn8.background = getDrawable(R.color.white)
        btn8.isEnabled = true
        btn8.text =""

        btn9.background = getDrawable(R.color.white)
        btn9.isEnabled = true
        btn9.text =""

        activePlayer=1
        player1.clear()
        player2.clear()
        winner = -1

        Toast.makeText(this,"New Game Started",Toast.LENGTH_SHORT).show()
    }
}
