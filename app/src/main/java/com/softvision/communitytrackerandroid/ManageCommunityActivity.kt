package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import com.softvision.communitytrackerandroid.databinding.ActivityMainBinding
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding

class ManageCommunityActivity : AppCompatActivity() {

    lateinit var community: Spinner


    private lateinit var binding: ActivityManageCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      //  setContentView(R.layout.activity_manage_community)

        binding = ActivityManageCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //community = findViewById(R.id.spcommunity) as Spinner
        with(binding) {

            val communityManagers = arrayListOf<String>()
            arrayListOf("Baron Patrick Paredes")
            arrayListOf("Gilberto Bernardo Morales")
            arrayListOf("Lloyd Joseph Miguel")
            arrayListOf("Rennor Galicia")
            arrayListOf("Zack Zabala")

        }

        val adapter = ArrayAdapter (
            this@ManageCommunityActivity,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spcommunity.adapter = adapter
        }
//        val adapter = ArrayAdapter.createFromResource(
//            this@ManageCommunityActivity,
//            R.array.community_array,
//            android.R.layout.simple_spinner_item
//        )
//        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//        binding.spcommunity.adapter = adapter


//        with(binding) {
//           val spinner = spcommunity.selectedItem.toString()
            buttonSave.setOnClickListener {

                val communityManager = spcommunity.selectedItem.toString()
                val communityName = etNameOfCommunity.text.toString()
                val communityDescription = etCommunityDescription.text.toString()

                val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                    AlertDialog.Builder(it)

                }
                builder?.setTitle("Community")
                    ?.setMessage("Assigned To: $spinner.\n\n\n Description: ${etCommunityDescription.text.toString()}")

                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }
        }
    }





