package com.softvision.communitytrackerandroid.data.model

import com.google.gson.annotations.SerializedName

data class LoginRequest(

    @SerializedName("CsvEmail")
    val email: String,
    @SerializedName("PassKey")
    val password: String
)
