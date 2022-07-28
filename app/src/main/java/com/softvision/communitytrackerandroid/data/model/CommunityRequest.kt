package com.softvision.communitytrackerandroid.data.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.parcelize.Parcelize

@Parcelize
data class CommunityRequest(
    @SerializedName("CommunityName")
    var name: String = "",

    @SerializedName("CommunityMgrId")
    var managerId: Long = 0,

    @SerializedName("Description")
    var description: String = ""): Parcelable
