package com.fullstack.mvvm_sample.ui.draweractivity.chat

import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModel
import com.fullstack.mvvm_sample.databinding.FragmentChatBinding
import com.fullstack.mvvm_sample.ui.BaseFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ChatFragment : BaseFragment<FragmentChatBinding>(){


    override val viewModel: ChatViewModel by viewModels()
    override fun inflateBinding() =
        FragmentChatBinding.inflate(layoutInflater)


}