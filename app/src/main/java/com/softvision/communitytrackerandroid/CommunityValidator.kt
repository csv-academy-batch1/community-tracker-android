package com.softvision.communitytrackerandroid

object CommunityValidator {

    fun validateCommunity(community: Community): Boolean {
        if (community.name.isEmpty()) {
            return false
        }
        if (community.description.isEmpty()) {
            return false
        }
        return true
    }
}