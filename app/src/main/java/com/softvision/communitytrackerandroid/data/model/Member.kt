package com.softvision.communitytrackerandroid.data.model

data class Member(
    val firstName: String,
    val assignedTo: String,
    val hiredDate: String,
    val state: String,
    val jobLevelId: String,
    val project: String
)

//val peopleId: Int,
//val communityId: Int,
//val lastName: String,
//val firstName: String,
//val middleName: String,
//val hiredDate: String,
//val jobLevelId: Int,
//val workStateId: Int,
//val isActive: Boolean) {
//
//    fun getName(): String {
//        return ("$firstName $middleName $lastName")
//    }
//}