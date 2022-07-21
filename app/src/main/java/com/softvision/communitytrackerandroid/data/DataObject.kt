package com.softvision.communitytrackerandroid.data

import com.softvision.communitytrackerandroid.data.model.Member

object DataObject {

    var managerList = arrayOf(
        Member(0, "Community Assigned To"),
        Member(6, "Lloyd Miguel"),
        Member(7, "Gilbert Morales"),
        Member(8, "Rennor Galicia"),
        Member(9, "Florante Navaja"),
        Member(10, "Baron Paredes")
    )

    fun getAllData(): List<String> {
        val list = mutableListOf<String>()
        for (member in managerList) {
            list.add(member.name)
        }
        return list
    }

    fun getMemberAllData(): List<Member> {
        return managerList.toList()

    }
}
