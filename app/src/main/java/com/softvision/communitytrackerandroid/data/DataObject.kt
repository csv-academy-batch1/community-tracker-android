package com.softvision.communitytrackerandroid.data

object DataObject {

    var managerList = arrayOf(
        Member("Community Assigned To"),
        Member("Baron Paredes"),
        Member("Lloyd Miguel"),
        Member("Zack Zabala"),
        Member("Rennor Galicia"),
        Member("Gilbert Morales")
    )


    fun getAllData(): List<String> {
        val list = mutableListOf<String>()
        for (member in managerList) {
            list.add(member.managerData)
        }
        return list
    }
}
