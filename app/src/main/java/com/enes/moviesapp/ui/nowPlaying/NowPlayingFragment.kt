package com.enes.moviesapp.ui.nowPlaying

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.enes.moviesapp.R
import com.enes.moviesapp.adapter.recyclerAdapter.RcAllMoviesAdapter
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentNowPLayingBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class NowPLayingFragment : BaseFragment<FragmentNowPLayingBinding>() {
    override fun getViewBinding() = FragmentNowPLayingBinding.inflate(layoutInflater)

    private val viewModel: ViewModelNowPlaying by activityViewModels()
    private lateinit var rcAdapter: RcAllMoviesAdapter

    override fun rcView() {
        rcAdapter = RcAllMoviesAdapter()

        rcAdapter.onClickItem { movie, view ->
            val bundle = bundleOf("moviesId" to movie.id)
            view.findNavController().navigate(R.id.detailFragment, bundle)
        }
        binding.rcView.apply {
            adapter = rcAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
    }

    override fun setObsever() {
        viewModel.liveMovieList.observe(viewLifecycleOwner, { moviesList ->
            rcAdapter.submitList(moviesList)
        })
        viewModel.loading.observe(viewLifecycleOwner, {
            viewModel.progresBar(it)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            NowPLayingFragment().apply {
                arguments = Bundle().apply {
                }
            }
    }

}