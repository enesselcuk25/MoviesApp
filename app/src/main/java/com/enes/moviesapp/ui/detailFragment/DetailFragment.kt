package com.enes.moviesapp.ui.detailFragment

import android.widget.Toast
import androidx.fragment.app.viewModels
import com.bumptech.glide.Glide
import com.enes.moviesapp.BuildConfig.IMAGE_BASE
import com.enes.moviesapp.R
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DetailFragment : BaseFragment<FragmentDetailBinding>() {
    override fun getViewBinding() = FragmentDetailBinding.inflate(layoutInflater)

    private val viewModelDetail: ViewModelDetail by viewModels()
    private var isLiked = false

    override fun rcView() {
        arguments?.let {
            val arguments = arguments?.getInt("moviesId")
            viewModelDetail.fetchMoviesData(arguments)

            viewModelDetail.getFavorite(arguments) { isLiked ->
                try {
                    this.isLiked = isLiked
                    if (isLiked) {
                        binding.liked.setImageResource(R.drawable.liked_icon)
                    } else {
                        binding.liked.setImageResource(R.drawable.like_icon)
                    }
                } catch (ex: Exception) {
                    Toast.makeText(requireContext(), ex.toString(), Toast.LENGTH_LONG).show()
                }

            }

            binding.liked.setOnClickListener {
                isLiked = if (isLiked) {
                    binding.liked.setImageResource(R.drawable.like_icon)
                    viewModelDetail.deleteFavori(arguments)
                    false
                } else {
                    viewModelDetail.addMoviesFavorite()
                    binding.liked.setImageResource(R.drawable.liked_icon)
                    true
                }
            }
        }
    }

    override fun setObsever() {
        viewModelDetail.liveMovies.observe(viewLifecycleOwner, { movieList ->
            movieList?.let {
                binding.apply {
                    Glide.with(requireActivity())
                        .load(IMAGE_BASE + it.poster_path)
                        .error(R.drawable.ic_launcher_background)
                        .into(this.imageView)

                    textVAverage.text = it.vote_average.toString()
                    textVData.text = it.release_date
                    textVOverview.text = it.overview
                    textVTitle.text = it.title
                }
            }
        })
    }
}