package com.example.weatherapp.view

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.weatherapp.R
import com.example.weatherapp.app.App
import com.example.weatherapp.data.Weather
import com.example.weatherapp.databinding.FragmentDetailsBinding
import com.example.weatherapp.viewmodel.AppState
import com.example.weatherapp.viewmodel.DetailsViewModel
import com.example.weatherapp.viewmodel.factory.DetailsViewModelFactory
import com.squareup.picasso.Picasso

class DetailsFragment : Fragment() {

    companion object {
        const val BUNDLE_EXTRA = "weather"

        fun newInstance(bundle: Bundle): DetailsFragment {
            val fragment = DetailsFragment()
            fragment.arguments = bundle
            return fragment
        }
    }

    private val vmFactory: DetailsViewModelFactory =
        App.appDependenciesComponents.detailsVMFactory()
    private val viewModel: DetailsViewModel by viewModels { vmFactory }
    private lateinit var weatherBundle: Weather
    private var _binding: FragmentDetailsBinding? = null
    private val binding
        get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater, container, false)
        return binding.root

    }

    @RequiresApi(Build.VERSION_CODES.N)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        weatherBundle = arguments?.getParcelable<Weather>(BUNDLE_EXTRA) ?: Weather()
        viewModel.detailsLiveData.observe(viewLifecycleOwner) { renderData(it) }
        viewModel.getWeatherFromRemoteSource(weatherBundle.city.lat, weatherBundle.city.lon)
    }

    private fun renderData(appState: AppState) {
        when (appState) {
            is AppState.Success -> {
                binding.main.show()
                binding.loadingLayout.hide()
                setWeather(appState.weatherData[0])
                viewModel.saveHistory(
                    Weather(
                        weatherBundle.city,
                        appState.weatherData[0].temperature,
                        appState.weatherData[0].feelsLike,
                        appState.weatherData[0].condition
                    )
                )
            }
            is AppState.Loading -> {
                binding.main.hide()
                binding.loadingLayout.show()
            }
            is AppState.Error -> {
                binding.main.show()
                binding.loadingLayout.hide()
                binding.main.showSnackBar(getString(R.string.error), getString(R.string.reload)) {
                    viewModel.getWeatherFromRemoteSource(
                        weatherBundle.city.lat,
                        weatherBundle.city.lon
                    )
                }
            }
        }
    }

    private fun setWeather(weather: Weather) {
        with(binding) {
            weatherBundle.city.let { city ->
                cityName.text = city.name
                cityCoordinates.text = String.format(
                    getString(R.string.city_coordinates),
                    city.lat.toString(),
                    city.lon.toString()
                )
            }
            weather.let {
                temperatureValue.text = it.temperature.toString()
                feelsLikeValue.text = it.feelsLike.toString()
                weatherCondition.text = it.condition
            }
            Picasso
                .get()
                .load("https://freepngimg.com/thumb/city/36275-3-city-hd.png")
                .into(headerIcon)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}