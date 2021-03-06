package com.enes.moviesapp.ui.upComing

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
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

    private val viewModel: ViewModelUpComing by activityViewModels()
   private lateinit var rcAdapter: RcAllMoviesAdapter

    override fun rcView() {
        rcAdapter = RcAllMoviesAdapter()
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
            rcAdapter.submitList(moviesList)
        })

        viewModel.loading.observe(viewLifecycleOwner, {
            viewModel.progresBar(it)
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