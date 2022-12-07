package com.fullstack.mvvm_sample.ui.authentication.signin

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.fullstack.mvvm_sample.R
import com.fullstack.mvvm_sample.databinding.FragmentSignInBinding
import com.fullstack.mvvm_sample.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class SignInFragment : BaseFragment<FragmentSignInBinding>() {

    override val viewModel: SignInViewModel by viewModels()
    override fun inflateBinding() =
        FragmentSignInBinding.inflate(layoutInflater)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init(){
        binding.signInButton.setOnClickListener{

        }
    }
}