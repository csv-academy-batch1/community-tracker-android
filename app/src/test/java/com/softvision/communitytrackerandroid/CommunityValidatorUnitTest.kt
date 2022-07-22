package com.softvision.communitytrackerandroid

import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.util.CommunityValidator
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class CommunityValidatorUnitTest {

    @Test
    fun communityValidator_validCommunity_returnTrue() {
        val community = Community(name = "Mobile", managerId = 1, description = "Mobile Developer")

        assertTrue(CommunityValidator.validateCommunity(community))
    }


    @Test
    fun communityValidator_validCommunity_nameIsEmpty_returnFalse() {
        val community = Community(name = "", managerId = 1, description = "Mobile Developer")

        assertFalse(CommunityValidator.validateCommunity(community))
    }

    @Test
    fun communityValidator_validCommunity_managerIsEmpty_returnFalse() {
        val community = Community(name = "Mobile", managerId = 0, description = "Mobile Developer")

        assertFalse(CommunityValidator.validateCommunity(community))
    }




    @Test
    fun communityValidator_validCommunity_descriptionIsEmpty_returnFalse() {
        val community = Community(name = "Mobile", managerId = 1, description = "")

        assertFalse(CommunityValidator.validateCommunity(community))
    }


}