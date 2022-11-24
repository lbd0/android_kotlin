package kr.ac.hallym.prac04_exer

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MotionEvent
import android.widget.TextView
import androidx.viewbinding.ViewBinding
import kr.ac.hallym.prac04_exer.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        var moveX = 0f
        var moveY = 0f

        binding.textView.setOnTouchListener { v, event ->
            when(event.action) {
                MotionEvent.ACTION_DOWN -> {
                    moveX = v.x - event.rawX
                    moveY = v.y - event.rawY
                }
                MotionEvent.ACTION_MOVE -> {
                    v.animate()
                        .x(event.rawX + moveX)
                        .y(event.rawY + moveY)
                        .setDuration(0)
                        .start()
                    binding.textView2.setText(" ")
                }
            }
            true
        }
    }
    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {

            MotionEvent.ACTION_MOVE -> {
                findViewById<TextView>(R.id.textView2).setText("사각형 위에서 드래그 하세요.")
            }
        }

        return super.onTouchEvent(event)
    }

}
