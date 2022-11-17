package com.fullstack.mvvm_sample.ui.authentication

import androidx.activity.viewModels
import com.fullstack.mvvm_sample.databinding.ActivityAuthenticationBinding
import com.fullstack.mvvm_sample.ui.BaseActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class AuthenticationActivity : BaseActivity<ActivityAuthenticationBinding>() {
    override val viewModel: AuthenticationViewModel by viewModels()
    override fun createBinding(): ActivityAuthenticationBinding =
        ActivityAuthenticationBinding.inflate(layoutInflater)
}