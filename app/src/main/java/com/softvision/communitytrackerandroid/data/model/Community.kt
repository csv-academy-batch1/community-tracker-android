package com.softvision.communitytrackerandroid.data.model

import com.google.gson.annotations.SerializedName

data class Community(
    @SerializedName("communityId")
    var id: Int = 0,

    @SerializedName("communityName")
    var name: String = "",

    @SerializedName("communityManager")
    var manager: String = "",

    @SerializedName("description")
    var description: String = "") {

    override fun toString(): String {
        return ("$name $manager $description")
    }
}
