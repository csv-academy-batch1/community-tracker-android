package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.softvision.communitytrackerandroid.data.Community
import com.softvision.communitytrackerandroid.data.DataObject
import com.softvision.communitytrackerandroid.data.Member
import com.softvision.communitytrackerandroid.data.api.ApiInterface
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding
import tayabas.anthony.retrofitsample.data.api.RetrofitClient

class ManageCommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManageCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val communityManager = DataObject.getAllData()
            val  adapter = ArrayAdapter(this@ManageCommunityActivity,R.layout.custom_simple_spinner_item, communityManager)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter

            btsave.setOnClickListener {
                val communityName = editTextNameOfCommunity.text.toString()
                val managerName = spinner.selectedItem.toString()
                val description = editDescriptionOfCommunity.text.toString()
                // val manager = Member(managerName)
                val community = Community(name = communityName, manager = managerName, description = description)

                // TODO Community Validation
                addCommunity(community)

                // If invalid
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

    fun addCommunity(community: Community) {
        var retrofit = RetrofitClient.getInstance()
        var apiInterface = retrofit.create(ApiInterface::class.java)

        lifecycleScope.launchWhenCreated {
            try {
                val response = apiInterface.addCommunity(community)
                if (response.isSuccessful()) {
                    val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                        AlertDialog.Builder(it)
                    }

                    builder?.setTitle("Create Community")
                        ?.setMessage("Successful")
                    val dialog: AlertDialog? = builder?.create()
                    dialog?.setOnDismissListener {
                        // finish()
                    }
                    dialog?.show()
                } else {
                    Toast.makeText(
                        this@ManageCommunityActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            }catch (Ex:Exception){
                Log.e("Error",Ex.localizedMessage)
            }
        }
    }
}