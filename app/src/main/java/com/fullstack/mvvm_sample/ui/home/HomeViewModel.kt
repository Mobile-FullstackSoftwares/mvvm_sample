package com.fullstack.mvvm_sample.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.fullstack.mvvm_sample.network.api.ApiService
import com.fullstack.mvvm_sample.network.api.ApiServiceInterface
import com.fullstack.mvvm_sample.network.api.ResultWrapper
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val api: ApiService
) : ViewModel() {


    fun getNetworkData(){
        viewModelScope.launch {
            when(val response = api.fetchUserContent(1)){
                is ResultWrapper.Success->{
                    val data = response.value?.data
                    data?.let {
                        println(it)
                    }
                }
                is ResultWrapper.GenericError->{

                }
                is ResultWrapper.NetworkError->{

                }
                is ResultWrapper.ObjectError->{

                }
            }
        }
    }
}