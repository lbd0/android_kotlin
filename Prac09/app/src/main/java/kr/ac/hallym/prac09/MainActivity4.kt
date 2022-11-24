package kr.ac.hallym.prac09

import android.inputmethodservice.InputMethodService
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import kr.ac.hallym.prac09.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    lateinit var binding : ActivityMain4Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main4)
        binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.showInputButton.setOnClickListener {
            binding.editView.requestFocus()
            manager.showSoftInput(binding.editView, InputMethodManager.SHOW_IMPLICIT)
        }

        binding.hideInputButton.setOnClickListener {
            manager.hideSoftInputFromWindow(currentFocus?.windowToken, InputMethodManager.HIDE_NOT_ALWAYS)
        }
    }
}