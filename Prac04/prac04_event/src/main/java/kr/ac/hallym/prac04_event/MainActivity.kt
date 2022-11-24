package kr.ac.hallym.prac04_event

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.widget.Toast
import kr.ac.hallym.prac04_event.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    // 뒤로가기 버튼 누른 시각을 저장하는 속성
    var initTime = 0L
    // 멈춘 시각을 저장하는 속성
    var pauseTime = 0L

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
     //   setContentView(R.layout.activity_main)

        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startButton.setOnClickListener {
            binding.chronometer.base = SystemClock.elapsedRealtime() + pauseTime
            binding.chronometer.start()

            binding.startButton.isEnabled = false
            binding.stopButton.isEnabled = true
            binding.resetButton.isEnabled = true
        }

        binding.stopButton.setOnClickListener {
            pauseTime = binding.chronometer.base - SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = true
        }

        binding.resetButton.setOnClickListener {
            pauseTime = 0L
            binding.chronometer.base = SystemClock.elapsedRealtime()
            binding.chronometer.stop()

            binding.startButton.isEnabled = true
            binding.stopButton.isEnabled = false
            binding.resetButton.isEnabled = false
        }
    }

    // 뒤로가기 버튼 이벤트 핸들러
    override fun onBackPressed() {
        // 뒤로가기 버튼을 처음 눌렀거나 누른지 3초가 지났을 때
        if(System.currentTimeMillis() - initTime > 3000) {
            Toast.makeText(this, "종료하려면 한 번 더 누르세요!!", Toast.LENGTH_SHORT).show()
            initTime = System.currentTimeMillis()
        }
        else
            super.onBackPressed()
    }
}