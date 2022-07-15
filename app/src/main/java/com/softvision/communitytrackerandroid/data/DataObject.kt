package com.softvision.communitytrackerandroid.data

object DataObject {

    var managerList = listOf(
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
