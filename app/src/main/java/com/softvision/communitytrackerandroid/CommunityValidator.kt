package com.softvision.communitytrackerandroid

import com.softvision.communitytrackerandroid.data.Community

object CommunityValidator {

    fun validateCommunity(community: Community) : Boolean {
        return !(community.name.isEmpty() || community.manager.isEmpty() || community.description.isEmpty())
    }
}