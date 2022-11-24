package kr.ac.hallym.prac07

import android.content.Context
import android.graphics.*
import android.graphics.drawable.Drawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.res.ResourcesCompat
import androidx.core.view.ViewCompat
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kr.ac.hallym.prac07.databinding.ActivityMain3Binding
import kr.ac.hallym.prac07.databinding.ItemMainBinding

class MainActivity3 : AppCompatActivity() {
    val binding3 by lazy {
        ActivityMain3Binding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main3)
        setContentView(binding3.root)

        val contents = mutableListOf<String>()
        for(i in 1..9) {
            contents.add("item: $i")
        }
        binding3.recyclerView.layoutManager = LinearLayoutManager(this)
        binding3.recyclerView.adapter = MyAdapter(contents)
        binding3.recyclerView.addItemDecoration(
            //DividerItemDecoration(this, LinearLayoutManager.VERTICAL)
            MyDecoration(this)
        )
    }
}

class MyViewHolder(val binding: ItemMainBinding) : RecyclerView.ViewHolder(binding.root)

class MyAdapter(val contents: MutableList<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun getItemCount(): Int {
        Log.d("week11", "init contents size: ${contents.size}")
        return contents.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
    = MyViewHolder(ItemMainBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        Log.d("week11", "onBindViewHolder: $position")
        val binding = (holder as MyViewHolder).binding

        binding.itemData.text = contents[position]

        binding.itemRoot.setOnClickListener {
            Log.d("kkang", "item root click: $position")
        }
    }
}

class MyDecoration(val context: Context) : RecyclerView.ItemDecoration() {
    override fun onDraw(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDraw(c, parent, state)
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.stadium), 0f, 0f, null)
    }

    override fun onDrawOver(c: Canvas, parent: RecyclerView, state: RecyclerView.State) {
        super.onDrawOver(c, parent, state)

        val width = parent.width
        val height = parent.height

        val img: Drawable? = ResourcesCompat.getDrawable(context.resources, R.drawable.kbo, null)
        val imgWidth = img?.intrinsicWidth
        val imgHeight = img?.intrinsicHeight

        val left = width/2 - imgWidth?.div(2) as Int
        val top = height/2 - imgHeight?.div(2) as Int
        c.drawBitmap(BitmapFactory.decodeResource(context.resources, R.drawable.kbo), left.toFloat(), top.toFloat(), null)
    }

    override fun getItemOffsets(
        outRect: Rect,
        view: View,
        parent: RecyclerView,
        state: RecyclerView.State
    ) {
        super.getItemOffsets(outRect, view, parent, state)
        val index = parent.getChildAdapterPosition(view) + 1
        if(index%3 == 0)
            outRect.set(10, 10, 10, 60)
        else
            outRect.set(10, 10, 10,0)
        view.setBackgroundColor(Color.LTGRAY)
        ViewCompat.setElevation(view, 20.0f)
    }
}