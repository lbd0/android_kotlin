package kr.ac.hallym.prac14

import android.content.ContextParams
import android.graphics.Bitmap
import android.media.Image
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.textclassifier.ConversationActions
import android.widget.ImageView
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.*
import kr.ac.hallym.prac14.databinding.ActivityMainBinding
import org.json.JSONObject
import java.util.HashMap

class MainActivity : AppCompatActivity() {
    lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.testButton.setOnClickListener {
            var url = "https://jsonplaceholder.typicode.com/posts"
/*
            val stringRequest = StringRequest(Request.Method.GET, url,
                Response.Listener<String> {
                    Log.d("week15", "server data : $it")
                },
                Response.ErrorListener { error ->
                    Log.d("week15", "error...$error")
                }
            )*/
/*
            val stringRequest = object : StringRequest(Request.Method.POST, url,
                Response.Listener<String> {
                    Log.d("week15", "server data : $it")
                },
                Response.ErrorListener { error ->
                    Log.d("week15", "error...$error")
                }
            ) {
                override fun getParams(): MutableMap<String, String> {
                    return mutableMapOf<String, String>("one" to "hello", "two" to "world")
                }
            }

            val queue = Volley.newRequestQueue(this)
            queue.add(stringRequest)*/
/*
            url = "https://via.placeholder.com/600/92c952.jpg"
            val imageRequest = ImageRequest(url,
            Response.Listener { response -> binding.imageView.setImageBitmap(response) },
            0,
            0,
            ImageView.ScaleType.CENTER_CROP,
            null,
            Response.ErrorListener { error -> Log.d("week15", "error...$error") })

            val queue = Volley.newRequestQueue(this)
            queue.add(imageRequest)*/

            // network image view
/*            val queue = Volley.newRequestQueue(this)
            val imgMap = HashMap<String?, Bitmap?>()
            val imageLoader = ImageLoader(queue, object : ImageLoader.ImageCache {
                override fun getBitmap(url: String?): Bitmap? {
                    return imgMap[url]
                }

                override fun putBitmap(url: String?, bitmap: Bitmap?) {
                    imgMap[url] = bitmap
                }
            })
            binding.networkImageView.setImageUrl(url, imageLoader)
*/

            url = "https://jsonplaceholder.typicode.com/posts/1"
            val jsonRequest = JsonObjectRequest(Request.Method.GET,url, null,
            Response.Listener<JSONObject> { response ->
                val title = response.getString("title")
                val boby = response.getString("body")
                Log.d("week15", "[title]: $title, [boby]: $boby")},
            Response.ErrorListener { error -> Log.d("week15","error...$error") })

            val queue = Volley.newRequestQueue(this)
            queue.add(jsonRequest)
        }


    }
}
