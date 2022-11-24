package kr.ac.hallym.prac03

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //setContentView(R.layout.linear_layout)
        //setContentView(R.layout.relative_layout)
        /*setContentView(R.layout.frame_layout)

        findViewById<Button>(R.id.button).setOnClickListener {
            findViewById<Button>(R.id.button).visibility = View.INVISIBLE
            findViewById<ImageView>(R.id.imageView).visibility = View.VISIBLE
        }

        findViewById<ImageView>(R.id.imageView).setOnClickListener {
            findViewById<Button>(R.id.button).visibility = View.VISIBLE
            findViewById<ImageView>(R.id.imageView).visibility = View.INVISIBLE
        }*/
        //setContentView(R.layout.grid_layout)
    }
}