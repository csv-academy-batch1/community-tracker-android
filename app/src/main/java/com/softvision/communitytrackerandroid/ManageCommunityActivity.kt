package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.appcompat.app.AlertDialog
import com.softvision.communitytrackerandroid.Data.Community
import com.softvision.communitytrackerandroid.Data.DataObject
import com.softvision.communitytrackerandroid.Data.Member
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding

// TODO Add Retrofit SDK dependencies and initial source code
class ManageCommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManageCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            // TODO Spinner or dropdown using dynamic array list (Create Data Class/ Model for Managers)
            val communityManager = DataObject.getAllData()
            val  adapter = ArrayAdapter(this@ManageCommunityActivity,R.layout.custom_simple_spinner_item, communityManager)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
//            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
//                override fun onItemSelected(
//                    parent: AdapterView<*>?,
//                    view: View?,
//                    position: Int,
//                    id: Long
//                ) {
//
//                }
//
//                override fun onNothingSelected(parent: AdapterView<*>?) {
//
//                }
//
//            }


            btsave.setOnClickListener {
                // TODO Create Data Class/ Model for Community
                val communityName = editTextNameOfCommunity.text.toString()
                val managerName = spinner.selectedItem.toString()
                val description = editDescriptionOfCommunity.text.toString()
                val manager = Member(managerName)
                val community = Community(name = communityName, manager = manager, description = description)

                val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                    AlertDialog.Builder(it)
                }

                builder?.setTitle("Community")
                    ?.setMessage("Community Name: ${community.name}\nCommunity Assigned To: ${community.manager}\n\nCommunity Description: ${community.description}\n")
                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }
        }

    }
}