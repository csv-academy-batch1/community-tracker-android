package com.softvision.communitytrackerandroid.data.model

import android.os.Parcel
import android.os.Parcelable
import com.google.gson.annotations.SerializedName


data class Community(
    @SerializedName("communityId")
    var id: Int = 0,

    @SerializedName("communityName")
    var name: String = "",

    @SerializedName("communityManager")
    var managerId: Int = 0,

    @SerializedName("communityDescription")
    var description: String = ""): Parcelable {

    constructor(parcel: Parcel) : this(
        parcel.readInt(),
        parcel.readString()!!,
        parcel.readInt(),
        parcel.readString()!!
    ) {
    }

    override fun toString(): String {
        return ("$name $managerId $description")
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeInt(id)
        parcel.writeString(name)
        parcel.writeInt(managerId)
        parcel.writeString(description)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Community> {
        override fun createFromParcel(parcel: Parcel): Community {
            return Community(parcel)
        }

        override fun newArray(size: Int): Array<Community?> {
            return arrayOfNulls(size)
        }
    }
}
