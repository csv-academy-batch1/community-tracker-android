package com.softvision.communitytrackerandroid.data

import com.google.gson.annotations.SerializedName

data class Community(
    @SerializedName("CommunityId")
    var id: Int = 0,

    @SerializedName("CommunityName")
    val name: String,

    @SerializedName("CommunityManager")
    val manager: String,

    @SerializedName("Description")
    val description: String) {

    override fun toString(): String {
        return ("$name $manager $description")
    }
}
