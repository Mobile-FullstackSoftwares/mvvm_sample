package com.fullstack.mvvm_sample.ui.draweractivity.map

import androidx.fragment.app.viewModels
import com.fullstack.mvvm_sample.databinding.FragmentMapBinding
import com.fullstack.mvvm_sample.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MapFragment : BaseFragment<FragmentMapBinding>(){

    override val viewModel: MapViewModel by viewModels()
    override fun inflateBinding() =
        FragmentMapBinding.inflate(layoutInflater)
}