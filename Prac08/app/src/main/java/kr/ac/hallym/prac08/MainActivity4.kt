package kr.ac.hallym.prac08

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import kr.ac.hallym.prac08.databinding.ActivityMain4Binding

class MainActivity4 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main4)
        val binding = ActivityMain4Binding.inflate(layoutInflater)
        setContentView(binding.root)

        val tabLayout = binding.tabs
        val viewPager = binding.viewpager
        viewPager.adapter = MyFragmentPagerAdapter(this)

        TabLayoutMediator(tabLayout, viewPager) { tab, position ->
            tab.text = "Tab${(position+1)}"
        }.attach()
    }

}

class MyFragmentPagerAdapter(activity: FragmentActivity) : FragmentStateAdapter(activity) {
    val fragments : List<Fragment>
    init {
        fragments = listOf(OneFragment(), TwoFragment(), ThreeFragment())
    }
    override fun getItemCount(): Int = fragments.size

    override fun createFragment(position: Int): Fragment = fragments[position]

}