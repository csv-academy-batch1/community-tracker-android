package com.softvision.communitytrackerandroid.data.model

import com.google.gson.annotations.SerializedName

data class LoginResponse(

    @SerializedName("CommunityMgrId")
    val communityId: Int,

    @SerializedName("RoleType")
    val roleType: String
)
