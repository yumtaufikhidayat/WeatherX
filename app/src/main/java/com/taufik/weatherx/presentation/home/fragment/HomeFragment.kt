package com.taufik.weatherx.presentation.home.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.weatherx.R
import com.taufik.weatherx.data.local.entity.WeatherEntity
import com.taufik.weatherx.databinding.FragmentHomeBinding
import com.taufik.weatherx.model.details.weather.Coord
import com.taufik.weatherx.model.details.weather.DetailWeathersResponse
import com.taufik.weatherx.model.details.weather.Main
import com.taufik.weatherx.model.details.weather.Weather
import com.taufik.weatherx.presentation.home.adapter.HomeAdapter
import com.taufik.weatherx.presentation.home.viewmodel.HomeViewModel
import com.taufik.weatherx.utils.navigateToDetail
import com.taufik.weatherx.utils.showError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<HomeViewModel>()
    private val homeAdapter by lazy { HomeAdapter {
        navigateToDetail(
            name = it.name,
            lat = it.coord.lat,
            lon = it.coord.lon
        )
    } }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigateToSearch()
        initSavedCityWeather()
        showSavedCityWeather()
    }

    private fun navigateToSearch() {
        binding.toolbarHome.imgAddWeather.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                findNavController().navigate(R.id.searchFragment)
            }
        }
    }

    private fun initSavedCityWeather() {
        binding.rvSavedCityWeather.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = homeAdapter
        }
    }

    private fun showSavedCityWeather() {
        viewModel.getAllSavedCityWeather().observe(viewLifecycleOwner) { weatherEntity ->
            if (!weatherEntity.isNullOrEmpty()) {
                homeAdapter.submitList(mapList(weatherEntity))
            } else {
                showError(TAG, "Error")
            }
        }
    }

    private fun mapList(list: List<WeatherEntity>): ArrayList<DetailWeathersResponse> {
        val listSavedCities = ArrayList<DetailWeathersResponse>()
        list.forEach { cities ->
            val weather = Weather(
                icon = cities.weatherIcon
            )
            val listWeather = listOf(weather)
            val coord = Coord(
                lat = cities.lat,
                lon = cities.lon
            )
            val main = Main(
                temp = cities.weatherDegree
            )

            val listSavedCitiesMapped = DetailWeathersResponse(
                id = cities.id,
                name = cities.cityName,
                weather = listWeather,
                coord = coord,
                main = main
            )
            listSavedCities.add(listSavedCitiesMapped)
        }
        return listSavedCities
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private var TAG = "home"
    }
}