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

            var communityManagers = arrayListOf<String>()
            arrayListOf("Baron Patrick Paredes")
            arrayListOf("Gilberto Bernardo Morales")
            arrayListOf("Lloyd Joseph Miguel")
            arrayListOf("Rennor Galicia")
            arrayListOf("Zack Zabala")

            val adapter = ArrayAdapter (this@ManageCommunityActivity, android.R.layout.simple_spinner_item, communityManagers)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spcommunity.adapter = adapter

            buttonSave.setOnClickListener {

                val communityManagers = spcommunity.selectedItem.toString()
                val communityName = etNameOfCommunity.text.toString()
                val communityDescription = etCommunityDescription.text.toString()

                val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                    AlertDialog.Builder(it)

                }
                builder?.setTitle("Community Name: $communityManagers")
                    ?.setMessage("Assigned To: $communityManagers.\n\n\n Description: ${etCommunityDescription.text.toString()}")

                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }
        }
    }
}
