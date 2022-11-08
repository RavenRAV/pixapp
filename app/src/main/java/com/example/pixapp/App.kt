package com.example.pixapp

import android.app.Application
import com.example.pixapp.model.PixaApi
import com.example.pixapp.model.RetrofitService

class App :Application() {

    companion object{
        lateinit var api: PixaApi
    }

    override fun onCreate() {
        super.onCreate()
        api = RetrofitService().getApi()
    }
}