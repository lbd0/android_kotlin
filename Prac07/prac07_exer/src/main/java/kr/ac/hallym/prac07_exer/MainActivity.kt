package kr.ac.hallym.prac07_exer

import android.content.Context
import android.graphics.Canvas
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.prac07_exer.databinding.ActivityMainBinding
import kr.ac.hallym.prac07_exer.databinding.ItemRecyclerviewBinding

class MainActivity : AppCompatActivity() {
    val binding1 by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(binding1.root)
        val contents = mutableListOf<String>("Mobile Programming", "Algorithm", "Embedded System")
        val contents_sub = mutableListOf<String>("2022년 2학기", "2022년 2학기", "2022년 2학기")

        binding1.recyclerView.layoutManager = LinearLayoutManager(this)
        binding1.recyclerView.adapter = MyAdapter(contents, contents_sub)
        binding1.recyclerView.addItemDecoration(MyDecoration(this))
    }
}
class MyViewHolder(val binding: ItemRecyclerviewBinding): RecyclerView.ViewHolder(binding.root)

class MyAdapter(val contents: MutableList<String>, val contents_sub: MutableList<String>): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        Log.d("week11", "init contents size: ${contents.size}")
        return contents.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = MyViewHolder(ItemRecyclerviewBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("week11", "onBindViewHolder: $position")
        val binding = (holder as MyViewHolder).binding

        binding.itemData.text = contents[position]
        binding.itemSubdata.text = contents_sub[position]

        binding.itemRoot.setOnClickListener {
            Log.d("week11", "item root click: $position")
        }
    }
}

class MyDecoration(val context:Context) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)
    }
}