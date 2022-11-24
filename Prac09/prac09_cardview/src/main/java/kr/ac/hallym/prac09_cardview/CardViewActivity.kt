package kr.ac.hallym.prac09_cardview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.hallym.prac09_cardview.databinding.ActivityCardViewBinding

class CardViewActivity : AppCompatActivity() {
    lateinit var binding : ActivityCardViewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_card_view)
        binding = ActivityCardViewBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val contents1 = mutableListOf<Int>(R.drawable.smartpot, R.drawable.exitzombie, R.drawable.helifire)
        val contents2 = mutableListOf<String>("Smart Pot", "Exit Zombie", "Heli Fire")
        val contents3 = mutableListOf<Int>(R.string.smart_pot, R.string.exit_zombie, R.string.heli_fire)

        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        binding.recyclerView.adapter  = MyAdapter(contents1, contents2, contents3)
        binding.recyclerView.addItemDecoration(
            DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
        )
    }
}