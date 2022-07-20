package com.softvision.communitytrackerandroid.data.model

import com.google.gson.annotations.SerializedName

class Communities(
    @SerializedName("communities")
    var communities: List<Community>,) {
}