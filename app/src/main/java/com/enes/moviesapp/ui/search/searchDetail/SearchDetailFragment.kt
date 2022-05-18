package com.enes.moviesapp.ui.search.searchDetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.navArgs
import com.enes.moviesapp.R
import com.enes.moviesapp.databinding.FragmentSearchDetailBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SearchDetailFragment : BottomSheetDialogFragment() {


    private lateinit var binding: FragmentSearchDetailBinding
    private val searchViewModel: SearchViewModel by activityViewModels()

    private val searchArgs: SearchDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_search_detail, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setSearch()

    }

    private fun setSearch() {

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            resultDetail = searchViewModel
            setHasOptionsMenu(true)
        }
        searchArgs.SearchDetailData.let { resultDetails ->
            val result = mutableListOf(resultDetails)
            searchViewModel.setDataSearch(result)
        }
    }
}