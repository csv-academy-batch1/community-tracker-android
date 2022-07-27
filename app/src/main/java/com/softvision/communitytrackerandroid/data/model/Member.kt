package com.softvision.communitytrackerandroid.data.model

import com.google.gson.annotations.SerializedName

data class Member(
    val id: Long,
    val name: String) {

    override fun toString(): String {
        return name
    }
}

