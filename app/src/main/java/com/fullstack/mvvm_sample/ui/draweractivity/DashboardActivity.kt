package com.fullstack.mvvm_sample.ui.draweractivity

import androidx.activity.viewModels
import com.fullstack.mvvm_sample.databinding.ActivityDashboardBinding
import com.fullstack.mvvm_sample.databinding.ActivityHomeBinding
import com.fullstack.mvvm_sample.ui.BaseActivity
import com.fullstack.mvvm_sample.ui.home.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class DashboardActivity : BaseActivity<ActivityDashboardBinding>() {
    override val viewModel: DashboardViewModel by viewModels()
    override fun createBinding(): ActivityDashboardBinding =
        ActivityDashboardBinding.inflate(layoutInflater)

}