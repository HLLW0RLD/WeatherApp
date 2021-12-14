package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.weatherapp.R
import com.example.weatherapp.data.Weather
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val weather = Weather(10, "Msk")

        btn.setOnClickListener {
            textView.text = weather.town + " : " + weather.temperature
        }

        consolWriter(weather.town)
    }

    fun consolWriter(word : String){
        for(i in 1..10){
            println(word)
        }
    }
}