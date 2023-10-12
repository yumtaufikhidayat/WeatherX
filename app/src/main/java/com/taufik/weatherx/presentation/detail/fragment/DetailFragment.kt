package com.taufik.weatherx.presentation.detail.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.weatherx.R
import com.taufik.weatherx.data.NetworkResult
import com.taufik.weatherx.data.remote.service.UrlEndpoint
import com.taufik.weatherx.databinding.FragmentDetailBinding
import com.taufik.weatherx.presentation.detail.adapter.DailyWeatherAdapter
import com.taufik.weatherx.presentation.detail.adapter.HourlyWeatherAdapter
import com.taufik.weatherx.presentation.detail.viewmodel.DetailViewModel
import com.taufik.weatherx.utils.loadImage
import com.taufik.weatherx.utils.showError
import dagger.hilt.android.AndroidEntryPoint
import kotlin.math.roundToInt


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
                            imgWeatherIcon.loadImage(
                                requireContext(),
                                getString(
                                    R.string.text_image_icon,
                                    UrlEndpoint.WEATHER_BASE_URL,
                                    UrlEndpoint.WEATHER_ICON,
                                    data?.weather?.first()?.icon
                                )
                            )

                            val weatherDegree = data?.main?.temp
                            val weatherDegreeInt = weatherDegree?.roundToInt()
                            val weatherDegreeStr = weatherDegreeInt.toString()
                            tvWeatherDegree.text = String.format(
                                "%s %s",
                                weatherDegreeStr,
                                "\u00B0C"
                            )
                        }
                    }

                    is NetworkResult.Error -> showError(TAG, it.message)
                }
            }
        }
    }

    private fun initHourlyAdapter() {
        binding.rvHourlyWeather.apply {
            layoutManager =
                LinearLayoutManager(requireContext(), LinearLayoutManager.HORIZONTAL, false)
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
                }
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