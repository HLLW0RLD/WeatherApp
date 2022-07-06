package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.example.weatherapp.app.App
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.view.adapter.HistoryFragmentAdapter
import com.example.weatherapp.viewmodel.AppState
import com.example.weatherapp.viewmodel.HistoryViewModel
import com.example.weatherapp.viewmodel.factory.HistoryViewModelFactory

class HistoryFragment : Fragment(){

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private val vmFactory: HistoryViewModelFactory = App.appDependenciesComponents.historyVMFactory()
    private val viewModel: HistoryViewModel by viewModels { vmFactory }
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