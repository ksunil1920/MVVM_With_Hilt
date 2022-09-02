package com.sky.kahabat.view.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.sky.kahabat.databinding.ActivityMainBinding
import com.sky.kahabat.util.NetworkResult
import com.sky.kahabat.view.adapter.CustomAdapter
import com.sky.kahabat.viewModel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private var customAdapter: CustomAdapter = CustomAdapter()
    private lateinit var mainViewModel: MainViewModel
    private lateinit var _bindings: ActivityMainBinding
    private val binding get() = _bindings


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _bindings = ActivityMainBinding.inflate(layoutInflater)
        val view = _bindings.root
        setContentView(view)
        mainViewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        binding.recycler.layoutManager = LinearLayoutManager(this)
        binding.recycler.adapter = customAdapter
        mainViewModel.quotesViewModelLiveData.observe(this) {

            when (it) {
                is NetworkResult.Success -> {
                    if (it.data!!.results.isEmpty()) {
                        binding.tvNoDataFound.visibility = View.VISIBLE
                    }
                    customAdapter.updateItems(it.data.results)
                    binding.progressCircular.visibility = View.GONE
                }
                is NetworkResult.Error -> {
                    binding.tvNoDataFound.visibility=View.VISIBLE
                    binding.tvNoDataFound.text = it.message.toString()
                    binding.progressCircular.visibility = View.GONE
                }
                is NetworkResult.Loading -> {
                    binding.progressCircular.visibility = View.VISIBLE
                }
            }


        }
    }

}