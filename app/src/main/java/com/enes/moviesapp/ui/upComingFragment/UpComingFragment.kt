package com.enes.moviesapp.ui.upComingFragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.enes.moviesapp.R
import com.enes.moviesapp.adapter.recyclerAdapter.RcAllMoviesAdapter
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentUpComingBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UpComingFragment : BaseFragment<FragmentUpComingBinding>() {
    override fun getViewBinding() = FragmentUpComingBinding.inflate(layoutInflater)

    private val viewModel: ViewModelUpComing by viewModels()
    lateinit var rcAdapter: RcAllMoviesAdapter

    override fun rcView() {
        rcAdapter = RcAllMoviesAdapter(requireContext())
        rcAdapter.onClickItem { movies, view ->
            val bundle = bundleOf("moviesId" to movies.id)
            view.findNavController().navigate(R.id.detailFragment, bundle)

        }
        binding.rcView.apply {
            adapter = rcAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun setObsever() {
        viewModel.liveMoviesList.observe(viewLifecycleOwner, { moviesList ->
            rcAdapter.moviesList = moviesList
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            UpComingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}