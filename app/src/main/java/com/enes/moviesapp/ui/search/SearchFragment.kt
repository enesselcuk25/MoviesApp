package com.enes.moviesapp.ui.search

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.core.widget.addTextChangedListener
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enes.moviesapp.R
import com.enes.moviesapp.databinding.FragmentSearchBinding
import com.enes.moviesapp.ui.search.adapter.SearchAdapter
import com.enes.moviesapp.util.Resource
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Job
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SearchFragment : Fragment() {

    private lateinit var binding: FragmentSearchBinding
    private lateinit var rcAdapter: SearchAdapter
    private val searchViewModel: SearchViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_search, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        navigate()
        searchView()

        binding.recyclerView.apply {
            adapter = rcAdapter
            layoutManager = LinearLayoutManager(context)
        }

    }

    private fun navigate() {
        rcAdapter = SearchAdapter(SearchAdapter.OnClick {
            searchViewModel.setNavigate(it)
        })

        searchViewModel.navigate.observe(viewLifecycleOwner, {
            val action =
                SearchFragmentDirections
                    .actionSearchFragmentToSearchDetailFragment(it)
            view?.findNavController()?.navigate(action)
        })
    }


    private fun searchView() {
        var job: Job? = null
        binding.searchView.addTextChangedListener { edittable ->
            job?.cancel()
            job = MainScope().launch {
                delay(500L)
                edittable?.let {

                    if (edittable.toString().isNotEmpty() && edittable.length > 3) {
                        searchViewModel.getSearch(edittable.toString())
                    }
                }
            }

        }

        searchViewModel.search.observe(viewLifecycleOwner, {
            when (it) {
                is Resource.Success -> {
                    it.data.let { details ->
                        details?.results?.let { listResult ->
                            rcAdapter.submitList(listResult)
                        }
                    }
                }
                is Resource.Error -> {
                    Toast.makeText(requireContext(), "OOPS ", Toast.LENGTH_SHORT).show()
                }
            }
        })
    }
}