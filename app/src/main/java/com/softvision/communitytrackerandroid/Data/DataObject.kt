package com.softvision.communitytrackerandroid.Data

object DataObject {

    var managerList = listOf(
        Member("Community Assigned To"),
        Member("Baron Paredes"),
        Member("Lloyd Miguel"),
        Member("Zack Zabala"),
        Member("Rennor Galicia"),
        Member("Gilbert Morales")
    )


    fun getAllData(): List<Member> {

        return managerList
    }
}
