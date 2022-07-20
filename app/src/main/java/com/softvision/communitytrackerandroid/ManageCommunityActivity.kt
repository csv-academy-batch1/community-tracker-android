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
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.data.DataObject
import com.softvision.communitytrackerandroid.data.api.ApiHelper
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding
import com.softvision.communitytrackerandroid.util.CommunityValidator

class ManageCommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageCommunityBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManageCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {
            val communityManager = DataObject.getAllData()
            // TODO Change Manager names(String) into Member instance
            val adapter = object: ArrayAdapter<String>(
                this@ManageCommunityActivity,
                android.R.layout.simple_spinner_dropdown_item,
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
                    } else {
                        spinner.setBackgroundResource(R.drawable.bg_spinner)
                    }
                }
            }
            editTextNameOfCommunity.setOnFocusChangeListener { _, focused ->
                if (!focused) {
                    validName()
                }
            }

            btsave.setOnClickListener {
                val communityName = editTextNameOfCommunity.text.toString()
                var managerName = spinner.selectedItem.toString()
                if (managerName.equals(communityManager[0])) {
                    managerName = ""
                }
                val description = editDescriptionOfCommunity.text.toString()
                val community = Community(name = communityName, manager = managerName, description = description)

                if (communityName.isEmpty()) {
                    editTextNameOfCommunity.error = "Required Field"
                    editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border_error)
                } else {
                    editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border)
                }

                if (managerName.isEmpty()) {
                    spinner.setBackgroundResource(R.drawable.bg_spinner_error)
                } else {
                    spinner.setBackgroundResource(R.drawable.bg_spinner)
                }

                if (CommunityValidator.validateCommunity(community)) {
                    // addCommunity(community)
                    // TODO Remove finish() method and use addCommunity() method
                    finish()
                }
            }
        }
    }

    private fun validName() {
        val communityName = binding.editTextNameOfCommunity.text.toString()
        return if (communityName.isEmpty()) {
            binding.editTextNameOfCommunity.error = "ERROR"
            binding.editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border_error)
        } else {
            binding.editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border)
        }
    }

    fun addCommunity(community: Community) {
        lifecycleScope.launchWhenCreated {
            try {
                val response = ApiHelper.apiInterface.addCommunity(community)
                if (response.isSuccessful()) {
                    val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                        AlertDialog.Builder(it)
                    }

                    builder?.setTitle("Create Community")
                        ?.setMessage("Successful")
                    val dialog: AlertDialog? = builder?.create()
                    dialog?.setOnDismissListener {
                        setResult(RESULT_OK)
                        finish()
                    }
                    dialog?.show()
                } else {
                    Toast.makeText(
                        this@ManageCommunityActivity,
                        response.errorBody().toString(),
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (ex: Exception){
                Log.e("Error", ex.localizedMessage)
            }
        }
    }
}