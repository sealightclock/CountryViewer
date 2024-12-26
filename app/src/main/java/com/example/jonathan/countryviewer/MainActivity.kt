package com.example.jonathan.countryviewer

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.jonathan.countryviewer.databinding.ActivityMainBinding
import com.example.jonathan.countryviewer.presentation.view.CountryAdapter
import com.example.jonathan.countryviewer.presentation.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {
    // Create a ViewModel:
    private val viewModel: CountryViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // View binding:
        val binding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(binding.root)

        // Observe the live data:
        viewModel.countries.observe(this) { countries ->
            binding.recyclerView.apply {
                //Set layout manager and adapter for RecyclerView
                layoutManager = LinearLayoutManager(this@MainActivity)
                adapter = CountryAdapter(countries)
            }
        }
    }
}
