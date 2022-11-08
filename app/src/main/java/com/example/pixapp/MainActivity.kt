package com.example.pixapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.pixapp.databinding.ActivityMainBinding
import com.example.pixapp.model.PixaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var adapter = PhotoAdapter(arrayListOf())

    var page = 1
    var perPage = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
//                override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
//                    super.onScrolled(recyclerView, dx, dy)
//
//                    if (dy > 0) {
//                        requestByImage(++page)
//                    }
//                }

                override fun onScrollStateChanged(recyclerView: RecyclerView, newState: Int) {
                    super.onScrollStateChanged(recyclerView, newState)
                    if (!recyclerView.canScrollVertically(1)) {
                        requestByImage(++page)
                        Toast.makeText(this@MainActivity, " ", Toast.LENGTH_LONG).show()
                    }
                }
            })


            pageBtn.setOnClickListener {
                requestByImage(++page)
            }

            photoBtn.setOnClickListener {
                page = 1
                requestByImage(page)
            }
        }
    }

    private fun ActivityMainBinding.requestByImage(page: Int) {
        App.api.getImages(keyWord = photoEt.text.toString(), page = page)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>, response: Response<PixaModel>
                ) {
                    response.body()?.hits?.let { listImageModel ->
                        listImageModel.forEach {
                            adapter.addImage(it)
                        }
                        binding.recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }


            })
    }
}