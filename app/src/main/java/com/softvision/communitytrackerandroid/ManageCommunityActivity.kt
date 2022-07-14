package com.softvision.communitytrackerandroid

import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
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
            val adapter = object: ArrayAdapter<String>(
                this@ManageCommunityActivity,
                R.layout.custom_simple_spinner_item,
                communityManager)
            {
                override fun isEnabled(position: Int): Boolean {
                    // Disable the first item from Spinner
                    // First item will be used for hint
                    return position != 0
                }

                override fun getDropDownView(
                    position: Int,
                    convertView: View?,
                    parent: ViewGroup
                ): View {
                    val view: TextView =
                        super.getDropDownView(position, convertView, parent) as TextView
                    // set the color of first item in the drop down list to gray
                    if (position == 0) {
                        view.setTextColor(Color.GRAY)
                    } else {
                        // here it is possible to define color for other items by
                        // view.setTextColor(Color.RED)
                    }
                    return view
                }
            }
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spinner.adapter = adapter
            spinner.onItemSelectedListener = object: AdapterView.OnItemSelectedListener{
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val value = parent!!.getItemAtPosition(position).toString()
                    if (value.equals(communityManager[0])) {
                        (view as TextView).setTextColor(Color.GRAY)
                    }
                }
            }

            btsave.setOnClickListener {
                val communityName = editTextNameOfCommunity.text.toString()
                var managerName = spinner.selectedItem.toString()
                if (managerName.equals(communityManager[0])) {
                    managerName = ""
                }
                val description = editDescriptionOfCommunity.text.toString()
                // val manager = Member(managerName)
                val community = Community(name = communityName, manager = managerName, description = description)

                // TODO Community Validation
                // addCommunity(community)

                // If invalid
                val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                    AlertDialog.Builder(it)
                }

                builder?.setTitle("Community")
                    ?.setMessage("Community Name: ${community.name}\nCommunity Assigned To: ${community.manager}\nCommunity Description: ${community.description}\n")
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