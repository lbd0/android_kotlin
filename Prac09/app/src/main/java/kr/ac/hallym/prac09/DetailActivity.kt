package kr.ac.hallym.prac09

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kr.ac.hallym.prac09.databinding.ActivityDetailBinding
import kr.ac.hallym.prac09.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    lateinit var binding : ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_detail)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val data1 = intent.getStringExtra("data1")
        val data2 = intent.getIntExtra("data2", 0)
        Toast.makeText(this, "Received: data1: $data1, data2: $data2", Toast.LENGTH_SHORT).show()

        val data3 = intent.getStringExtra("data3")
        binding.textView.setText("DetailActivity\n$data3")

        binding.detailButton.setOnClickListener {
            intent.putExtra("result", "이름: ${binding.nameText.getText()}\n학번: ${binding.numText.getText()}")
            setResult(RESULT_OK, intent)
            finish()
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return super.onSupportNavigateUp()
    }
}