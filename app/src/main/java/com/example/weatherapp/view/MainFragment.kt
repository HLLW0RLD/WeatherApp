package com.example.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.data.Weather
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.viewmodel.AppState
import com.example.weatherapp.viewmodel.MainViewModel
import com.google.android.material.snackbar.Snackbar

class MainFragment : Fragment() {

    companion object {
        fun newInstance() = MainFragment()
    }



    private val viewModel: MainViewModel by lazy {
        ViewModelProvider(this).get(MainViewModel::class.java)
    }

    private var _binding: FragmentMainBinding? = null

    private val binding
        get() = _binding!!

    private val adapter = MainFragmentAdapter()

    private var isDataSetRus: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        adapter.setOnItemViewClickListener { weather ->
            activity?.supportFragmentManager?.apply {
                beginTransaction()
                    .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                        putParcelable(DetailsFragment.BUNDLE_EXTRA, weather)
                    }))
                    .addToBackStack("")
                    .commitAllowingStateLoss()
            }
    }

        binding.mainFragmentRecyclerView.adapter = adapter
        binding.mainFragmentFAB.setOnClickListener {
            changeWeatherDataSet()
        }
        val observer = Observer<AppState> { a ->
            renderData(a)
        }
        viewModel.getData().observe(viewLifecycleOwner, observer)
        viewModel.getWeatherFromLocalSourceWorld()
    }



    private fun changeWeatherDataSet() {
        if (isDataSetRus) {
            viewModel.getWeatherFromLocalSourceRus()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_earth)
        } else {
            viewModel.getWeatherFromLocalSourceWorld()
            binding.mainFragmentFAB.setImageResource(R.drawable.ic_russia)
        }
        isDataSetRus = !isDataSetRus
    }

    private fun renderData(data: AppState) {
        when (data) {
            is AppState.Success -> {
                binding.loadingLayout.hide()
                adapter.setWeather(data.weatherData)
            }
            is AppState.Loading -> {
                binding.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.loadingLayout.hide()
                binding.mainFragmentFAB.showSnackBar("Error", "Reload") {
                    if (isDataSetRus) viewModel.getWeatherFromLocalSourceRus()
                    else viewModel.getWeatherFromLocalSourceWorld()
                }
            }
        }
    }
}