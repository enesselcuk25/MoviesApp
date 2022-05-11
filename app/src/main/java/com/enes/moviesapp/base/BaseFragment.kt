package com.enes.moviesapp.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<vBinding : ViewBinding> : Fragment() {

    private var _binding: vBinding? = null
    val binding get() = _binding!!

    abstract fun getViewBinding(): vBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = getViewBinding()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        rcView()
        setObsever()
        setViewpager()
        splashCreated()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    open fun rcView() {}
    open fun setObsever() {}
    open fun setViewpager() {}
    open fun splashCreated() {}




}


