package com.softvision.communitytrackerandroid.Data

data class Community(var id: Int = 0, val name: String, val manager: Member, val description: String) {
    override fun toString(): String {
        return ("$name $manager $description")
    }
}
