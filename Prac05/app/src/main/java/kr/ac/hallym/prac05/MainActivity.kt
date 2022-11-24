package kr.ac.hallym.prac05

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.DimenRes
import androidx.annotation.Dimension
import androidx.core.content.res.ResourcesCompat
import org.w3c.dom.Text

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main2)

         setContentView(R.layout.activity_main)

        findViewById<Button>(R.id.myButton).setOnClickListener {
            findViewById<TextView>(R.id.textView).text = getString(R.string.txt_data2)
            findViewById<TextView>(R.id.textView).setTextColor(ResourcesCompat.getColor(resources, R.color.black,null))
            //findViewById<TextView>(R.id.textView).textSize = resources.getDimension(R.dimen.txt_size)
            findViewById<TextView>(R.id.textView).setTextSize(Dimension.DP, resources.getDimension(R.dimen.txt_size))

            findViewById<ImageView>(R.id.myIamge).setImageDrawable(ResourcesCompat.getDrawable(resources, android.R.drawable.alert_light_frame, null))
            findViewById<TextView>(R.id.myText).text = getString(android.R.string.httpErrorBadUrl)

        }

    }
}