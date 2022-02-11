package com.enes.moviesapp.ui.populerFragment

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import com.enes.moviesapp.R
import com.enes.moviesapp.adapter.recyclerAdapter.RcAllMoviesAdapter
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentPopulerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PopularFragment : BaseFragment<FragmentPopulerBinding>() {

    override fun getViewBinding() = FragmentPopulerBinding.inflate(layoutInflater)

    private val viewModel: ViewModelPopular by viewModels()
    private lateinit var rcAdapter: RcAllMoviesAdapter

    override fun rcView() {
        rcAdapter = RcAllMoviesAdapter(requireContext())
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
        viewModel.liveMovieList.observe(viewLifecycleOwner, Observer { moviesList ->
            rcAdapter.moviesList = moviesList
        })
    }

    companion object {
        fun newInstance() =
            PopularFragment().apply {
                arguments = Bundle().apply { }
            }
    }


}