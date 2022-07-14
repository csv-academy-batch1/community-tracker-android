package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.lifecycleScope
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding
import data.api.ApiInterface
import data.api.RetrofitClient


class ManageCommunityActivity : AppCompatActivity() {

    private lateinit var binding: ActivityManageCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityManageCommunityBinding.inflate(layoutInflater)
        setContentView(binding.root)

        with(binding) {

            var communityManagers = arrayListOf<String>("Baron Patrick Paredes", "Gilberto Bernardo Morales","Lloyd Joseph Miguel","Rennor Galicia", "Zack Zabala")
//

            val adapter = ArrayAdapter(this@ManageCommunityActivity, android.R.layout.simple_spinner_item, communityManagers)
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            spcommunity.adapter = adapter


            buttonSave.setOnClickListener {

                val communityManagers = spcommunity.selectedItem.toString()
                val communityName = etNameOfCommunity.text.toString()
                val communityDescription = etCommunityDescription.text.toString()

                val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                    AlertDialog.Builder(it)

                }
                builder?.setTitle("Community")
                    ?.setMessage("Community Name $communityName \n Assigned To: $communityManagers.\n\n\n Description: ${etCommunityDescription.text.toString()}")

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
