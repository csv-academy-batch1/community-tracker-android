package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.softvision.communitytrackerandroid.databinding.ActivityMainBinding
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
            val communityManager = arrayOf("Baron Paredes", "Lloyd Miguel")

            ArrayAdapter.createFromResource(
                this@ManageCommunityActivity,
                R.array.communities_arrays,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                binding.spinner.adapter = adapter
            }

            ivLogo.visibility = View.VISIBLE

            btsave.setOnClickListener {
                // TODO Create Data Class/ Model for Community
                val test = spinner.selectedItem.toString()

                val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                    AlertDialog.Builder(it)
                }

                builder?.setTitle("Community Name is: ${editTextNameOfCommunity.text.toString()}")
                    ?.setMessage("Assigned To: $test\nCommunity Description: ${editDescriptionOfCommunity.text.toString()}")
                val dialog: AlertDialog? = builder?.create()

                dialog?.show()
            }
        }
    }
}