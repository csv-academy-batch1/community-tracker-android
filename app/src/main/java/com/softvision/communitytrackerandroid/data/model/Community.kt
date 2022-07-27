package com.softvision.communitytrackerandroid.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class Community(
    @SerializedName("CommunityId")
    var id: Long = 0,

    @SerializedName("CommunityName")
    var name: String = "",

    @SerializedName("CommunityMgrid")
    var managerId: Long = 0,

    @SerializedName("Description")
    var description: String = ""): Parcelable {

    override fun toString(): String {
        return ("$name $managerId $description")
    }
}
