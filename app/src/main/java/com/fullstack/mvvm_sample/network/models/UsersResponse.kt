package com.fullstack.mvvm_sample.network.models

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
data class UsersResponse(
    val page: Int? = null,
    val per_page: Int? = null,
    val total: Int? = null,
    val data: List<User>? = null,
    val support: Support? = null
): Parcelable

@Parcelize
data class User(
    val id: Int? = null,
    val email: String? = null,
    val first_name: String? = null,
    val last_name: String? = null,
    @SerializedName("avatar")
    val image: String? = null
): Parcelable

@Parcelize
data class Support(
    val url: String? = null,
    val text: String? = null
): Parcelable