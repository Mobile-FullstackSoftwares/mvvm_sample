package com.fullstack.mvvm_sample.utils

import androidx.lifecycle.MutableLiveData

typealias StateMachine = MutableLiveData<DataFetchState>

sealed class DataFetchState {
    object Loading: DataFetchState()
    class Success(val message: String? = null): DataFetchState()
    class Error(val error: String?): DataFetchState()
}