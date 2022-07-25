package com.softvision.communitytrackerandroid.data

import android.graphics.Color
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.data.model.Member

object DataObject {

    fun getAllManager(): List<Member> {
        return listOf(
            Member(0, "Community Assigned To"),
            Member(6, "Lloyd Miguel"),
            Member(7, "Gilbert Morales"),
            Member(8, "Rennor Galicia"),
            Member(9, "Florante Navaja"),
            Member(10, "Baron Paredes")
        )
    }

    fun getColor(): Array<Int> {
        return arrayOf(
            Color.parseColor("#ffc581"),
            Color.parseColor("#ffa382"),
            Color.parseColor("#ff8a84"),
            Color.parseColor("#f06e9c"),
            Color.parseColor("#d756f6"),
            Color.parseColor("#8b51f5"),
            Color.parseColor("#aedd94"),
            Color.parseColor("#75a9f9"),
            Color.parseColor("#4dd7fa")
        )
    }

    fun getCommunityList(): List<Community> {
        return listOf(
            Community(id = 1, name = "Enterprise.net", managerId = 6, description = ".Net Group"),
            Community(id = 2, name = "Full-Stack Web", managerId = 7, description = "Web Group"),
            Community(id = 3, name = "Quality Engineering", managerId = 8, description = "QE Group"),
            Community(id = 4, name = "Cloud and DevOps", managerId = 6, description = "DevOps Group"),
            Community(id = 5, name = "Big Data & Analytics", managerId = 7, description = "QE Group"),
            Community(id = 6, name = "Product Delivery", managerId = 8, description = "Android and iOS Developer"),
            Community(id = 7, name = "Enterprise Coffee", managerId = 6, description = ".Net Group"),
            Community(id = 8, name = "Mobile Cross Platform", managerId = 7, description = "Android and iOS Developer"),
            Community(id = 9, name = "Research and Developent", managerId = 8, description = "Android and iOS Developer")
        )
    }
}
