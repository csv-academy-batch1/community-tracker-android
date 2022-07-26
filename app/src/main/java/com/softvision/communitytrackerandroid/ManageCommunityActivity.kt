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
import com.softvision.communitytrackerandroid.data.model.CommunityRequest
import com.softvision.communitytrackerandroid.data.model.Member
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding
import com.softvision.communitytrackerandroid.util.CommunityValidator

class ManageCommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageCommunityBinding
    private var action: Int = MainActivity.ACTION_ADD_COMMUNITY
    private var selectedCommunity: Community? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManageCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val bundle = intent.extras
        if (bundle != null) {
            action = bundle.getInt("action", MainActivity.ACTION_ADD_COMMUNITY)
            selectedCommunity = bundle.getParcelable<Community>("community") ?: null
        }

        with(binding) {
            val communityManager = DataObject.getAllManager()
            val adapter = object : ArrayAdapter<Member>(
                this@ManageCommunityActivity,
                android.R.layout.simple_spinner_dropdown_item,
                communityManager
            ) {
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
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(parent: AdapterView<*>?) {
                }

                override fun onItemSelected(
                    parent: AdapterView<*>?,
                    view: View?,
                    position: Int,
                    id: Long
                ) {
                    val value = parent!!.getItemAtPosition(position).toString()
                    if (value.equals(communityManager[0].toString())) {
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

            btSave.setOnClickListener {
                val communityName = editTextNameOfCommunity.text.toString()
                val manager = spinner.selectedItem as Member
                val description = editDescriptionOfCommunity.text.toString()

                if (communityName.isEmpty()) {
                    editTextNameOfCommunity.error = "Required Field"
                    editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border_error)
                } else {
                    editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border)
                }

                if (manager.id == 0) {
                    spinner.setBackgroundResource(R.drawable.bg_spinner_error)
                } else {
                    spinner.setBackgroundResource(R.drawable.bg_spinner)
                }

                if (action == MainActivity.ACTION_ADD_COMMUNITY) {

                    val communityRequest = CommunityRequest(
                        name = communityName,
                        managerId = manager.id,
                        description = description,
                    )
                    if (CommunityValidator.validateCommunity(communityRequest)) {
                        addCommunity(communityRequest)
                    }

                } else if (action == MainActivity.ACTION_UPDATE_COMMUNITY) {

                    val community = Community(
                        name = communityName,
                        managerId = manager.id,
                        description = description,
                    )
                    if (CommunityValidator.validateCommunity(community)) {
//                        updateCommunity(community)
                    }

                }


            }

            if (action == MainActivity.ACTION_ADD_COMMUNITY) {
                tvTitle.setText(R.string.community_input_page)
                btSave.setText(R.string.save)
            } else if (action == MainActivity.ACTION_UPDATE_COMMUNITY) {
                editTextNameOfCommunity.setText(selectedCommunity?.name)
                editDescriptionOfCommunity.setText(selectedCommunity?.description)
                val index = getSpinnerIndex(communityManager)
                spinner.setSelection(index)
                tvTitle.setText(R.string.community_update_page)
                btSave.setText(R.string.update)
            }
        }
    }

    fun getSpinnerIndex(communityManager : List<Member>) : Int {
        for (i in communityManager.indices) {
            if (selectedCommunity?.managerId == communityManager.get(i).id) {
                return i
            }
        }
        return 0
    }

    private fun validName() {
        val communityName = binding.editTextNameOfCommunity.text.toString()
        return if (communityName.isEmpty()) {
            binding.editTextNameOfCommunity.error = "Required Field"
            binding.editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border_error)
        } else {
            binding.editTextNameOfCommunity.setBackgroundResource(R.drawable.rounded_border)
        }
    }

    fun addCommunity(community: CommunityRequest) {
        lifecycleScope.launchWhenCreated {
            try {
                val response = ApiHelper.apiInterface.addCommunity(community)
                if (response.isSuccessful && response.body() != null && response.body()!!.name.isNotEmpty()) {
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
                        "Add Community : Failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (ex: Exception){
                ex.localizedMessage?.let { Log.e("Error", it) }
                Toast.makeText(
                    this@ManageCommunityActivity,
                    "Add Community : Failed",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }

    fun updateCommunity(community: Community) {
        /*
        lifecycleScope.launchWhenCreated {
            try {
                val response = ApiHelper.apiInterface.updateCommunities(community)
                if (response.isSuccessful && response.body() != null) {
                    val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                        AlertDialog.Builder(it)
                    }

                    builder?.setTitle("Update Community")
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
                        "Update Community : Failed",
                        Toast.LENGTH_LONG
                    ).show()
                }
            } catch (ex: Exception){
                ex.localizedMessage?.let { Log.e("Error", it) }
                Toast.makeText(
                    this@ManageCommunityActivity,
                    "Update Community : Failed",
                    Toast.LENGTH_LONG
                ).show()
            }
        }

         */
    }
}
