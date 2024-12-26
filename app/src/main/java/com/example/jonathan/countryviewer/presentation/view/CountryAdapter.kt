package com.example.jonathan.countryviewer.presentation.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.jonathan.countryviewer.databinding.ItemCountryBinding
import com.example.jonathan.countryviewer.domain.datamodels.Country

/**
 * This is the adapter for the RecyclerView.
 */
class CountryAdapter(private val countries: List<Country>) : RecyclerView.Adapter<CountryAdapter.CountryViewHolder>() {
    class CountryViewHolder(private val binding: ItemCountryBinding) :
        RecyclerView.ViewHolder(binding.root) {

        // Bind data to the view:
        fun bind(country: Country) {
            binding.countryName.text = country.name
            binding.countryRegion.text = country.region
            binding.countryCode.text = country.code
            binding.countryCapital.text = country.capital
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CountryViewHolder {
        val binding = ItemCountryBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CountryViewHolder, position: Int) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int = countries.size
}
