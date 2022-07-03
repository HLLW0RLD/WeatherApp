package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.weatherapp.R
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.view.adapter.HistoryFragmentAdapter
import com.example.weatherapp.viewmodel.AppState
import com.example.weatherapp.viewmodel.HistoryViewModel
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.component.KoinComponent

class HistoryFragment : Fragment(), KoinComponent {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val viewModel: HistoryViewModel by viewModel<HistoryViewModel>()

    private var _binding: FragmentHistoryBinding? = null
    private val binding
        get() = _binding!!
    private var adapter = HistoryFragmentAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.historyFragmentRecyclerView.adapter = adapter
        viewModel.historyLiveData.observe(viewLifecycleOwner) {
            when (it) {
                is AppState.Success -> {
                    adapter.setData(it.weatherData)
                }
            }
        }
        viewModel.getDataHistory()
    }
}