package com.enes.moviesapp.ui.topRated

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.enes.moviesapp.R
import com.enes.moviesapp.adapter.recyclerAdapter.RcAllMoviesAdapter
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentTopMoviesBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class TopRatedMoviesFragment : BaseFragment<FragmentTopMoviesBinding>() {

    override fun getViewBinding() = FragmentTopMoviesBinding.inflate(layoutInflater)

    private lateinit var rcAdapter: RcAllMoviesAdapter
    private val moviesViewModel: ViewModelTopMovies by activityViewModels()


    override fun rcView() {
        rcAdapter = RcAllMoviesAdapter()
        rcAdapter.onClickItem { movie, view ->
            val bundle = bundleOf("moviesId" to movie.id)
            view.findNavController().navigate(R.id.detailFragment, bundle)
        }
        binding.rcViewAllMovies.apply {
            adapter = rcAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)

        }
    }

    override fun setObsever() {
        moviesViewModel.liveMoviesList.observe(viewLifecycleOwner, { moviesList ->
            rcAdapter.submitList(moviesList)

        })

        moviesViewModel.loading.observe(viewLifecycleOwner, {
            moviesViewModel.progresBar(it)
        })
    }

    companion object {
        fun newInstance() =
            TopRatedMoviesFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }
}