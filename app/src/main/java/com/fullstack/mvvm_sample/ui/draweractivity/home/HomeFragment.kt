package com.fullstack.mvvm_sample.ui.draweractivity.home

import androidx.fragment.app.viewModels
import com.fullstack.mvvm_sample.databinding.FragmentHomeBinding
import com.fullstack.mvvm_sample.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeFragment : BaseFragment<FragmentHomeBinding>(){

    override val viewModel: HomeViewModel by viewModels()
    override fun inflateBinding() =
        FragmentHomeBinding.inflate(layoutInflater)
}