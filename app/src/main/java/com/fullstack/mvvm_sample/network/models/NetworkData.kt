package com.fullstack.mvvm_sample.network.models

import java.io.Serializable

data class NetworkData (
    val name: String,
    val description: String,
    val image: String
): Serializable