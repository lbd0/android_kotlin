package kr.ac.hallym.prac10

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import kr.ac.hallym.prac10.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val db = openOrCreateDatabase("testdb", Context.MODE_PRIVATE, null)
        db.execSQL("create table if not exists USER_TB (" +
        "_id integer primary key autoincrement," +
        "name not null," +
        "phone)")

        val manager = getSystemService(INPUT_METHOD_SERVICE) as InputMethodManager

        binding.button1.setOnClickListener {
            db.execSQL("insert into USER_TB (name, phone) values (?, ?)",
            arrayOf<String>(binding.editName.getText().toString(), binding.editPhone.getText().toString()))
            manager.showSoftInput(binding.editPhone, InputMethodManager.SHOW_IMPLICIT)
            binding.editName.setText("")
            binding.editPhone.setText("")
            Toast.makeText(this, "데이터베이스에 저장되었습니다.", Toast.LENGTH_SHORT).show()
        }

        binding.button2.setOnClickListener {
            val cursor = db.rawQuery("select * from USER_TB", null)
            var i = 0
            binding.textView.setText("")
            while(cursor.moveToNext()) {
                val name = cursor.getString(1)
                val phone = cursor.getString(2)
                binding.textView.append("[${i++}] name: $name, phone: $phone\n")
                Log.d("week13", "name: $name, phone: $phone")
            }
        }
    }
}