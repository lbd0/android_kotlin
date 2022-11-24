package kr.ac.hallym.prac03_exer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.btnClear).setOnClickListener{
            findViewById<TextView>(R.id.textview).setText(" ")
        }

        findViewById<Button>(R.id.btnPer).setOnClickListener{
            findViewById<TextView>(R.id.textview).setText(" ")
        }

        findViewById<Button>(R.id.btnSlash).setOnClickListener{
            findViewById<TextView>(R.id.textview).setText(" ")
        }

        findViewById<Button>(R.id.btnStar).setOnClickListener{
            findViewById<TextView>(R.id.textview).setText(" ")
        }

        findViewById<Button>(R.id.btnMinus).setOnClickListener{
            findViewById<TextView>(R.id.textview).setText(" ")
        }

        findViewById<Button>(R.id.btnPlus).setOnClickListener{
            findViewById<TextView>(R.id.textview).setText(" ")
        }

        findViewById<Button>(R.id.btnEqual).setOnClickListener{
            findViewById<TextView>(R.id.textview).setText(" ")
        }

        findViewById<Button>(R.id.btn0).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("0")
        }

        findViewById<Button>(R.id.btn1).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("1")
        }

        findViewById<Button>(R.id.btn2).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("2")
        }

        findViewById<Button>(R.id.btn3).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("3")
        }

        findViewById<Button>(R.id.btn4).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("4")
        }

        findViewById<Button>(R.id.btn5).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("5")
        }

        findViewById<Button>(R.id.btn6).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("6")
        }

        findViewById<Button>(R.id.btn7).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("7")
        }

        findViewById<Button>(R.id.btn8).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("8")
        }

        findViewById<Button>(R.id.btn9).setOnClickListener{
            findViewById<TextView>(R.id.textview).append("9")
        }

        findViewById<Button>(R.id.btnDot).setOnClickListener{
            findViewById<TextView>(R.id.textview).append(".")
        }



    }
}