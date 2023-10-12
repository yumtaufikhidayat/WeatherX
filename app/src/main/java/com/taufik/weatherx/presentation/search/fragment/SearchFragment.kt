package com.taufik.weatherx.presentation.search.fragment

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.taufik.weatherx.data.NetworkResult
import com.taufik.weatherx.databinding.FragmentSearchBinding
import com.taufik.weatherx.presentation.search.adapter.SearchAdapter
import com.taufik.weatherx.presentation.search.viewmodel.SearchViewModel
import com.taufik.weatherx.utils.navigateToDetail
import com.taufik.weatherx.utils.showError
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private var _binding: FragmentSearchBinding? = null
    private val binding get() = _binding!!

    private val viewModel by viewModels<SearchViewModel>()
    private val searchAdapter by lazy {
        SearchAdapter {
            navigateToDetail(
                name = it.name,
                lat = it.lat,
                lon = it.lon
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        backToHome()
        initAdapter()
        observeSearchCity()
    }

    private fun backToHome() {
        binding.toolbarSearch.imgBack.apply {
            visibility = View.VISIBLE
            setOnClickListener {
                findNavController().popBackStack()
            }
        }
    }

    private fun initAdapter() {
        binding.rvSearchWeather.apply {
            layoutManager = LinearLayoutManager(requireContext())
            setHasFixedSize(true)
            adapter = searchAdapter
        }
    }

    private fun observeSearchCity() {
        binding.etSearchWeather.apply {
            showKeyboard()
            setOnEditorActionListener(TextView.OnEditorActionListener { _, actionId, _ ->
                if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                    hideKeyboard()
                    return@OnEditorActionListener true
                }
                false
            })
            addTextChangedListener(afterTextChanged = { text ->
                showSearchData(text)
            })
        }
    }

    private fun showSearchData(text: Editable?) {
        val query = text.toString()
        viewModel.apply {
            getCityName(query)
            searchCity.observe(viewLifecycleOwner) {
                when (it) {
                    is NetworkResult.Loading -> showLoading(true)
                    is NetworkResult.Success -> {
                        showLoading(false)
                        searchAdapter.submitList(it.data)
                    }

                    is NetworkResult.Error -> {
                        showLoading(false)
                        showError(TAG, it.message)
                    }

                    else -> {
                        showLoading(false)
                        showError(TAG, it.message)
                    }
                }
            }
        }
    }

    private fun showKeyboard() {
        binding.etSearchWeather.apply {
            requestFocus()
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
        }
    }

    private fun hideKeyboard() {
        binding.etSearchWeather.apply {
            clearFocus()
            val imm =
                requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(this.windowToken, 0)
        }
    }

    private fun showLoading(isShow: Boolean) {
        binding.pbLoading.visibility = if (isShow) View.VISIBLE else View.GONE
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        const val TAG = "search"
    }
}