package com.softvision.communitytrackerandroid

import com.softvision.communitytrackerandroid.data.Community

object CommunityValidator {

    fun validateCommunity(community: Community) : Boolean {
        if (community.manager.isEmpty()) {

        }

        return true
    }
}