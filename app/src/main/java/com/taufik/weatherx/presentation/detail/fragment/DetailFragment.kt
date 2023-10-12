package com.taufik.weatherx.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.weatherx.R
import com.taufik.weatherx.data.NetworkResult
import com.taufik.weatherx.data.remote.service.UrlEndpoint
import com.taufik.weatherx.databinding.FragmentDetailBinding
import com.taufik.weatherx.presentation.detail.adapter.DailyWeatherAdapter
import com.taufik.weatherx.presentation.detail.adapter.HourlyWeatherAdapter
import com.taufik.weatherx.presentation.detail.viewmodel.DetailViewModel
import com.taufik.weatherx.utils.loadWeatherIcon
import com.taufik.weatherx.utils.showError
import com.taufik.weatherx.utils.showToast
import com.taufik.weatherx.utils.toDegree
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailFragment : Fragment() {

    private var _binding: FragmentDetailBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<DetailViewModel>()
    private val hourlyWeatherAdapter by lazy { HourlyWeatherAdapter() }
    private val dailyWeatherAdapter by lazy { DailyWeatherAdapter() }

    private var cityName: String = ""
    private var lat: Double = 0.0
    private var lon: Double = 0.0
    private var isChecked = false

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        getBundleData()
        initToolbar()
        getCityWeather()
        initHourlyAdapter()
        getCityWeatherHourly()
        initDailyAdapter()
        getCityWeatherDaily()
    }

    private fun getBundleData() {
        cityName = arguments?.getString(CITY_NAME).orEmpty()
        lat = arguments?.getDouble(LAT) ?: 0.0
        lon = arguments?.getDouble(LON) ?: 0.0
    }

    private fun initToolbar() {
        binding.toolbarDetail.apply {
            imgBack.visibility = View.VISIBLE
            imgBack.setOnClickListener {
                findNavController().popBackStack()
            }
            tvTitle.text = cityName
        }
    }

    private fun getCityWeather() {
        viewModel.apply {
            getCityWeather(lat, lon, WEATHER_METRIC)
            cityWeather.observe(viewLifecycleOwner) {
                val data = it.data
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        binding.apply {
                            imgWeatherIcon.loadWeatherIcon(
                                requireContext(),
                                getString(
                                    R.string.text_image_icon,
                                    UrlEndpoint.WEATHER_BASE_URL,
                                    UrlEndpoint.WEATHER_ICON,
                                    data?.weather?.first()?.icon
                                )
                            )

                            val weatherDegree = data?.main?.temp
                            tvWeatherDegree.text = weatherDegree?.toDegree()

                            checkIsCitySaved()
                            checkCityWeatherSaved(data?.id ?: 0)
                            addCityWeatherToSave(
                                id = data?.id ?: 0,
                                cityName = cityName,
                                weatherIcon = getString(
                                    R.string.text_image_icon,
                                    UrlEndpoint.WEATHER_BASE_URL,
                                    UrlEndpoint.WEATHER_ICON,
                                    data?.weather?.first()?.icon
                                ),
                                lat = lat,
                                lon = lon,
                                weatherDegree = weatherDegree ?: 0.0
                            )
                        }
                    }
                    is NetworkResult.Error -> showError(TAG, it.message)
                    else -> {}
                }
            }
        }
    }

    private fun initHourlyAdapter() {
        binding.rvHourlyWeather.apply {
            layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
            setHasFixedSize(true)
        }
    }

    private fun getCityWeatherHourly() {
        viewModel.apply {
            getCityWeathersHourly(lat, lon, WEATHER_EXCLUDE, WEATHER_METRIC)
            cityWeatherHourly.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        hourlyWeatherAdapter.submitList(it.data?.hourly)
                        binding.rvHourlyWeather.adapter = hourlyWeatherAdapter
                    }
                    is NetworkResult.Error -> showError(TAG, it.message)
                    else -> {}
                }
            }
        }
    }

    private fun initDailyAdapter() {
        binding.rvDailyWeather.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
        }
    }

    private fun getCityWeatherDaily() {
        viewModel.apply {
            getCityWeathersDaily(lat, lon, WEATHER_METRIC)
            cityWeatherDaily.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> {}
                    is NetworkResult.Success -> {
                        dailyWeatherAdapter.submitList(it.data?.daily)
                        binding.rvDailyWeather.adapter = dailyWeatherAdapter
                    }
                    is NetworkResult.Error -> showError(TAG, it.message)
                    else -> {}
                }
            }
        }
    }

    private fun addCityWeatherToSave(
        id: Int,
        cityName: String,
        weatherIcon: String,
        weatherDegree: Double,
        lat: Double,
        lon: Double
    ) {
        binding.toolbarDetail.toggleSaveWeather.setOnClickListener {
            isChecked = !isChecked
            if (isChecked) {
                viewModel.saveCityWeather(
                    id,
                    cityName,
                    weatherIcon,
                    weatherDegree,
                    lat,
                    lon
                )
                requireContext().showToast("City Saved")
            } else {
                viewModel.removeSavedCityWeather(id)
                requireContext().showToast("City Removed")
            }
        }
    }

    private fun checkCityWeatherSaved(id: Int) {
        binding.toolbarDetail.apply {
            toggleSaveWeather.isVisible = true
            lifecycleScope.launch {
                viewModel.checkSavedCityWeather(id)
            }
        }
    }

    private fun checkIsCitySaved() {
        viewModel.citySavedId.observe(viewLifecycleOwner) { count ->
            if (count > 0) {
                binding.toolbarDetail.toggleSaveWeather.isChecked = true
                isChecked = true
            } else {
                binding.toolbarDetail.toggleSaveWeather.isChecked = false
                isChecked = false
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "detail"
        const val CITY_NAME = "city_name"
        const val LAT = "lat"
        const val LON = "lon"
        const val WEATHER_METRIC = "metric"
        const val WEATHER_EXCLUDE = "daily"
    }
}