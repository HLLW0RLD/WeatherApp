package com.example.weatherapp.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.data.Weather
import com.example.weatherapp.databinding.HistoryRecyclerItemBinding

class HistoryFragmentAdapter(private var weatherHistory: List<Weather>) : RecyclerView.Adapter<HistoryFragmentAdapter.MainViewHolder>() {

    private var onHistoryItemViewClickListener: (Weather) -> Unit = {}

    fun setOnHistoryItemViewClickListener(onHistoryItemViewClickListener: (Weather) -> Unit){
        this.onHistoryItemViewClickListener = onHistoryItemViewClickListener
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HistoryFragmentAdapter.MainViewHolder {
        val binding = HistoryRecyclerItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: HistoryFragmentAdapter.MainViewHolder, position: Int) {
        holder.bind(weatherHistory[position])
    }

    override fun getItemCount(): Int {
        return weatherHistory.size
    }

    inner class MainViewHolder(val binding: HistoryRecyclerItemBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(weather: Weather) {
            binding.apply {
                historyCityTitle.text = weather.city.name
                historyTemperatureTitle.text = weather.temperature.toString()
                root.setOnClickListener {
                    onHistoryItemViewClickListener(weather)
                }
            }
        }
    }
}