package kr.ac.hallym.prac04

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.CompoundButton
import android.widget.TextView
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        findViewById<View>(R.id.myView).setOnTouchListener { v:View, event:MotionEvent ->
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    Log.d("kkang", "Touch down event x: ${event.x}, rawX: ${event.rawX}")
                    findViewById<TextView>(R.id.textView).text = "Touch down event x: ${event.x}, rawX: ${event.rawX}"
                }
                MotionEvent.ACTION_UP -> {
                    Log.d("kkang", "Touch up event x: ${event.x}, rawX: ${event.rawX}")
                    findViewById<TextView>(R.id.textView).text = "Touch up event x: ${event.x}, rawX: ${event.rawX}"
                }
                MotionEvent.ACTION_MOVE -> {
                    Log.d("kkang", "Touch move event x: ${event.x}, rawX: ${event.rawX}")
                    findViewById<TextView>(R.id.textView).text = "Touch move event x: ${event.x}, rawX: ${event.rawX}"
                }
            }
            true
        }

        findViewById<CheckBox>(R.id.checkbox).setOnCheckedChangeListener{ v:CompoundButton, b:Boolean ->
            Log.d("kkang", "Checkbox 클릭")
            findViewById<TextView>(R.id.textView).text = "Checkbox 클릭"
        }

        findViewById<Button>(R.id.myButton).setOnClickListener {
            Log.d("kkang", "Click 이벤트")
            findViewById<TextView>(R.id.textView).text = "Click 이벤트"
        }

        findViewById<Button>(R.id.myButton).setOnLongClickListener {
            Log.d("kkang", "Long Click 이벤트")
            findViewById<TextView>(R.id.textView).text = "Long Click 이벤트"
            true
        }
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                Log.d("kkang", "Touch down event x: ${event.x}, rawX: ${event.rawX}")
                findViewById<TextView>(R.id.textView).text = "Touch down event x: ${event.x}, rawX: ${event.rawX}"
            }
            MotionEvent.ACTION_UP -> {
                Log.d("kkang", "Touch up event x: ${event.x}, rawX: ${event.rawX}")
                findViewById<TextView>(R.id.textView).text = "Touch up event x: ${event.x}, rawX: ${event.rawX}"
            }
            MotionEvent.ACTION_MOVE -> {
                Log.d("kkang", "Touch move event x: ${event.x}, rawX: ${event.rawX}")
                findViewById<TextView>(R.id.textView).text = "Touch move event x: ${event.x}, rawX: ${event.rawX}"
            }
        }

        return super.onTouchEvent(event)
    }

    override fun onKeyDown(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("kkang", "onKeyDown : $keyCode")
        findViewById<TextView>(R.id.textView).text = "onKeyDown :$keyCode"
        return super.onKeyDown(keyCode, event)
    }

    override fun onKeyUp(keyCode: Int, event: KeyEvent?): Boolean {
        Log.d("kkang", "onKeyUp : $keyCode")
        findViewById<TextView>(R.id.textView).text = "onKeyUp :$keyCode"
        return super.onKeyDown(keyCode, event)
    }
}