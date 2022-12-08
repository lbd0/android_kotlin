package kr.ac.hallym.prac14_network

import android.content.Context
import android.graphics.pdf.PdfDocument
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import kr.ac.hallym.prac14_network.databinding.FragmentRetrofitBinding
import retrofit2.Response
import retrofit2.Call
import retrofit2.Callback

class RetrofitFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
       // return inflater.inflate(R.layout.fragment_retrofit, container, false)

        val binding = FragmentRetrofitBinding.inflate(inflater, container, false)

        val call : Call<PageListModel> = MyApplication.networkService.getList(
            MyApplication.QUERY,
            MyApplication.API_KEY,
            1,
            10
        )

        call?.enqueue(object: Callback<PageListModel> {
            override fun onResponse(call:Call<PageListModel>, response: Response<PageListModel>) {
                if(response.isSuccessful) {
                    binding.retrofitRecyclerView.layoutManager = LinearLayoutManager(activity)
                    binding.retrofitRecyclerView.adapter = MyAdapter(activity as Context, response.body()?.articles)
                }
            }
            override fun onFailure(call: Call<PageListModel>, t: Throwable) {
                Log.d("week15", "error...")
            }
        })
        return binding.root
    }
}