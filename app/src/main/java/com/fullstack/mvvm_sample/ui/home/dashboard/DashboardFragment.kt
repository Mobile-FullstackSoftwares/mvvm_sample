package com.fullstack.mvvm_sample.ui.home.dashboard

import androidx.fragment.app.viewModels
import com.fullstack.mvvm_sample.databinding.FragmentDashboardBinding
import com.fullstack.mvvm_sample.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardFragment : BaseFragment<FragmentDashboardBinding>() {


    override val viewModel: DashBoardViewModel by viewModels()
    override fun inflateBinding() =
        FragmentDashboardBinding.inflate(layoutInflater)

}

