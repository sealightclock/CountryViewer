package com.example.jonathan.countryviewer

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.jonathan.countryviewer.data.repository.CountryRepository
import com.example.jonathan.countryviewer.presentation.view.CountryAdapter
import com.example.jonathan.countryviewer.presentation.viewmodel.CountryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: CountryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        viewModel = CountryViewModel(CountryRepository())
        //viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.getData()
        //}

        // TODO: Replace with actual list of countries
        val countries = viewModel.countries.value ?: emptyList()

        val recyclerView: RecyclerView = findViewById(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = CountryAdapter(countries)
    }

    override fun onResume() {
        super.onResume()

        //viewModel.viewModelScope.launch(Dispatchers.IO) {
            viewModel.getData()
        //}
    }
}

