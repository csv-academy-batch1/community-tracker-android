package com.softvision.communitytrackerandroid.data

import android.graphics.Color
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.data.model.Manager
import com.softvision.communitytrackerandroid.data.model.Member

object DataObject {

    fun getAllManager(): List<Manager> {
        return listOf(
            Manager(0, "Community Assigned To"),
            Manager(1, "Admin 1"),
            Manager(2, "Admin 2"),
            Manager(3, "Admin 3"),
            Manager(4, "Admin 4"),
            Manager(5, "Admin 5"),
            Manager(6, "Lloyd Miguel"),
            Manager(7, "Gilbert Morales"),
            Manager(8, "Rennor Galicia"),
            Manager(9, "Florante Navaja"),
            Manager(10, "Baron Paredes")
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

    fun getMemberList(): List<Member> {
        return listOf(
            Member("John Jay Rimorin","Lloyd Joseph Miguel", "08/02/2022"),
            Member("Hans Gustaf Capiral",  "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Rosemarie Osinsao", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Erwin Daza", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Kirstin Megga Ramos", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Samuel Marvin Augilos", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Bianca ANgela Yap", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Christian Sarzaba", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Lambert Cadalzo", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Rommel John Monsanto", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Brian Baldos", "Lloyd Joseph Miguel", "08/02/2022"),
            Member("Billy Malbataan", "Lloyd Joseph Miguel", "08/02/2022")
        )
    }
}
