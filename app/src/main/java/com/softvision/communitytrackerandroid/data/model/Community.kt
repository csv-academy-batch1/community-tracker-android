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

    @SerializedName("CommunityMgrId")
    var managerId: Long = 0,

    @SerializedName("CommunityManager")
    var manager: String = "",

    @SerializedName("Description")
    var description: String? = null
): Parcelable
