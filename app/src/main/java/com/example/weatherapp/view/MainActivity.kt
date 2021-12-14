package com.example.weatherapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.weatherapp.R
import com.example.weatherapp.data.Weather
import com.example.weatherapp.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val weather = Weather(10, "Msk")

        binding.btn.setOnClickListener {
            val result = (1..3).random()
            if (result == 1) {
                Toast.makeText(applicationContext, "Lost connection! Please try again", Toast.LENGTH_SHORT).show()
            } else {
                binding.textView.text = weather.town + " : " + weather.temperature
            }
        }

        consolWriter(weather.town)
    }

    fun consolWriter(word: String) {
        for (i in 1..10) {
            println(word)
        }
    }
}