package com.fullstack.mvvm_sample.ui.home

import androidx.activity.viewModels
import com.fullstack.mvvm_sample.databinding.ActivityHomeBinding
import com.fullstack.mvvm_sample.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class HomeActivity : BaseActivity<ActivityHomeBinding>() {
    override val viewModel: HomeViewModel by viewModels()
    override fun createBinding(): ActivityHomeBinding =
        ActivityHomeBinding.inflate(layoutInflater)

    override fun onResume() {
        super.onResume()
        viewModel.getNetworkData()
    }
}