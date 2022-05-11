package com.enes.moviesapp.ui.liked

import androidx.core.os.bundleOf
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enes.moviesapp.R
import com.enes.moviesapp.adapter.likedAdapter.RecyLikedAdapter
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentLikedBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class LikedFragment : BaseFragment<FragmentLikedBinding>() {

    override fun getViewBinding() = FragmentLikedBinding.inflate(layoutInflater)
    private lateinit var rcAdapter: RecyLikedAdapter
    private val viewModel: ViewModelLiked by activityViewModels()

    override fun rcView() {
        rcAdapter = RecyLikedAdapter()
        rcAdapter.onCLickItem { movieFavoriteEntity, view ->
            val bundle = bundleOf("moviesId" to movieFavoriteEntity.id)
            view.findNavController().navigate(R.id.detailFragment, bundle)

        }
        binding.rcView.apply {
            this.adapter = rcAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setObsever() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner, {
            rcAdapter.submitList(it)
        })
    }

    override fun onStart() {
        super.onStart()
        viewModel.getFavorite()
    }


}