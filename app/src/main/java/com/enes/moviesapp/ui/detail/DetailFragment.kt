package com.enes.moviesapp.ui.detail

import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import com.enes.moviesapp.R
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentDetailBinding
import com.enes.moviesapp.ui.MainActivity.Companion.mainActivity
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

    private val viewModelDetail: ViewModelDetail by activityViewModels()
    private var isLiked = false

    override fun rcView() {
        arguments?.let {
            val arguments = arguments?.getInt("moviesId")
            viewModelDetail.fetchMoviesData(arguments)

            viewModelDetail.getFavorite(arguments) { isLiked ->
                try {
                    this.isLiked = isLiked
                    if (isLiked) {
                        binding.liked.setImageResource(R.drawable.likediconeyellow)
                    } else {
                        binding.liked.setImageResource(R.drawable.ic_baseline_favorite_24)
                    }
                } catch (ex: Exception) {
                    Toast.makeText(requireContext(), ex.toString(), Toast.LENGTH_LONG).show()
                }

            }

            binding.liked.setOnClickListener {
                isLiked = if (isLiked) {
                    binding.liked.setImageResource(R.drawable.ic_baseline_favorite_24)
                    viewModelDetail.deleteFavori(arguments)
                    false
                } else {
                    viewModelDetail.addMoviesFavorite()
                    binding.liked.setImageResource(R.drawable.likediconeyellow)
                    true
                }
            }
        }

        viewModelDetail.loading.observe(viewLifecycleOwner, {
            mainActivity.show(it)
        })
    }

    override fun setObsever() {
        viewModelDetail.liveMovies.observe(viewLifecycleOwner, { movieList ->
            movieList?.let {
                binding.detailMovie = movieList
            }
        })
    }
}