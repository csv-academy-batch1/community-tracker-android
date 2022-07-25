package com.softvision.communitytrackerandroid.util

import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.data.model.CommunityRequest

object CommunityValidator {

    fun validateCommunity(community: Community) : Boolean {
        return community.name.isNotEmpty() && community.managerId > 0
    }

    fun validateCommunity(community: CommunityRequest) : Boolean {
        return community.name.isNotEmpty() && community.managerId > 0
    }
}