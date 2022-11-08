package com.example.pixapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.pixapp.databinding.ActivityMainBinding
import com.example.pixapp.model.PixaModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    var adapter = PhotoAdapter(listOf())
    
    var perPage = 3
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initClickers()
    }

    private fun initClickers() {
        with(binding) {
            pageBtn.setOnClickListener {
                requestByImage(++perPage)
            }

            photoBtn.setOnClickListener {
                perPage = 3
                requestByImage(perPage)
            }
        }
    }

    private fun ActivityMainBinding.requestByImage(perPage: Int){
        App.api.getImages(keyWord = photoEt.text.toString(), perPage = perPage)
            .enqueue(object : Callback<PixaModel> {
                override fun onResponse(
                    call: Call<PixaModel>, response: Response<PixaModel>
                ) {
                    response.body()?.hits?.let { listImageModel ->
                        adapter = PhotoAdapter(listImageModel)
                        binding.recyclerView.adapter = adapter
                    }
                }

                override fun onFailure(call: Call<PixaModel>, t: Throwable) {
                    Log.e("ololo", "onFailure: ${t.message}")
                }


            })
    }
}