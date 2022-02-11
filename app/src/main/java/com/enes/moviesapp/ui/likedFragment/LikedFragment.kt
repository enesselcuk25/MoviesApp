package com.enes.moviesapp.ui.likedFragment

import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.enes.moviesapp.R
import com.enes.moviesapp.adapter.likedAdapter.RecyLikedAdapter
import com.enes.moviesapp.base.BaseFragment
import com.enes.moviesapp.databinding.FragmentLikedBinding
import dagger.hilt.android.AndroidEntryPoint

/*
        düzeltilemsi gerekn kodlar
        - like image tıklayınca renk değişimi olmuyor
        -  beğendiği filimler aniden gelmiyor liked sayfasına
 */
@AndroidEntryPoint
class LikedFragment : BaseFragment<FragmentLikedBinding>() {

    override fun getViewBinding() = FragmentLikedBinding.inflate(layoutInflater)
    private lateinit var rcAdapter: RecyLikedAdapter
    private val viewModel: ViewModelLiked by viewModels()

    override fun rcView() {
        rcAdapter = RecyLikedAdapter(requireContext())
        rcAdapter.onCLik { movieFavoriteEntity, view ->
            val bundle = bundleOf("moviesId" to movieFavoriteEntity.id)
            view.findNavController().navigate(R.id.detailFragment, bundle)

        }

        binding.rcView.apply {
            this.adapter = rcAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }
    }

    override fun setObsever() {
        viewModel.moviesLiveData.observe(viewLifecycleOwner,  {
            rcAdapter.movieList = it
        })
    }
}