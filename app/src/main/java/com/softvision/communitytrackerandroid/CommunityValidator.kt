package com.softvision.communitytrackerandroid

<<<<<<< HEAD
object CommunityValidator {

    fun validateCommunity(community: Community): Boolean {

        if (community.name.isEmpty()) {
            return false
        }
        if (community.manager.isEmpty()) {
            return false
        }
        if (community.description.isEmpty()) {
            return false
        }
=======
import com.softvision.communitytrackerandroid.data.Community

object CommunityValidator {

    fun validateCommunity(community: Community) : Boolean {
        if (community.manager.isEmpty()) {

        }

>>>>>>> 235c1b741b6d016cc9fd32109ef8dfab90f98b9c
        return true
    }
}