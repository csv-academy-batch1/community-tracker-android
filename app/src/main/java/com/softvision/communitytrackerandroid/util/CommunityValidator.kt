package com.softvision.communitytrackerandroid.util

import com.softvision.communitytrackerandroid.data.model.Community

object CommunityValidator {

    fun validateCommunity(community: Community) : Boolean {
        return !(community.name.isEmpty() || community.manager.isEmpty() || community.description.isEmpty())
    }
}