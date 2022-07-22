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
        val community = Community(
            name = "Mobile",
            manager = "Zack Zabala",
            description = "Mobile Developer",
            managerId = 6
        )

        assertTrue(CommunityValidator.validateCommunity(community))
    }


    @Test
    fun communityValidator_validCommunity_nameIsEmpty_returnFalse() {
        val community = Community(
            name = "",
            manager = "Zack Zabala",
            description = "Mobile Developer",
            managerId = 6
        )

        assertFalse(CommunityValidator.validateCommunity(community))
    }

    @Test
    fun communityValidator_validCommunity_managerIsEmpty_returnFalse() {
        val community = Community(
            name = "Mobile",
            manager = "",
            description = "Mobile Developer",
            managerId = 6
        )

        assertFalse(CommunityValidator.validateCommunity(community))
    }




    @Test
    fun communityValidator_validCommunity_descriptionIsEmpty_returnFalse() {
        val community = Community(
            name = "Mobile",
            manager = "Zack Zabala",
            description = "",
            managerId = 6
        )

        assertFalse(CommunityValidator.validateCommunity(community))
    }


}