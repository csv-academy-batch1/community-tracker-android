package com.softvision.communitytrackerandroid

import android.accounts.AuthenticatorDescription
import com.google.gson.annotations.SerializedName
import java.lang.reflect.Member

data class Community(
    @SerializedName("CommunityID")
    var id: Int = 0,

    @SerializedName("CommunityName")
    var name: String,

    @SerializedName("CommunityManager")
    var manager: String,

    @SerializedName("CommunityDescription")
    var description: String) {

    override fun toString(): String {
        return ("$name $manager $description")
    }

}
