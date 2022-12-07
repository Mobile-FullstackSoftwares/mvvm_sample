package com.fullstack.mvvm_sample.ui.home.schedule

import androidx.fragment.app.viewModels
import com.fullstack.mvvm_sample.databinding.FragmentScheduleBinding
import com.fullstack.mvvm_sample.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ScheduleFragment : BaseFragment<FragmentScheduleBinding>() {

    override val viewModel: ScheduleViewModel by viewModels()
    override fun inflateBinding() =
        FragmentScheduleBinding.inflate(layoutInflater)

}

