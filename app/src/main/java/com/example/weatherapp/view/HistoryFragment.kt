package com.example.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherapp.R
import com.example.weatherapp.app.App
import com.example.weatherapp.data.repository.LocalRepositoryImpl
import com.example.weatherapp.databinding.FragmentHistoryBinding
import com.example.weatherapp.databinding.FragmentMainBinding
import com.example.weatherapp.view.adapter.HistoryFragmentAdapter

class HistoryFragment : Fragment() {

    companion object {
        fun newInstance() = HistoryFragment()
    }

    private var _binding: FragmentHistoryBinding? = null
    private val binding
        get() = _binding!!
    private var adapter =
        HistoryFragmentAdapter(LocalRepositoryImpl(App.getHistoryDAO()).getAllHistory())

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.historyFragmentRecyclerView.adapter = adapter
        adapter.apply {
            notifyDataSetChanged()
            setOnHistoryItemViewClickListener { weather ->
                activity?.supportFragmentManager?.apply {
                    beginTransaction()
                        .add(R.id.container, DetailsFragment.newInstance(Bundle().apply {
                            putParcelable(DetailsFragment.BUNDLE_EXTRA, weather)
                        }))
                        .addToBackStack("")
                        .commitAllowingStateLoss()
                }
            }

        }
    }
}