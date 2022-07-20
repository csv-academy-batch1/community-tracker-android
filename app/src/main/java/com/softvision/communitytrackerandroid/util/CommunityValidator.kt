package com.softvision.communitytrackerandroid.util

import com.softvision.communitytrackerandroid.data.model.Community

object CommunityValidator {

    fun validateCommunity(community: Community) : Boolean {
        return community.name.isNotEmpty() && community.managerId > 0
    }
}