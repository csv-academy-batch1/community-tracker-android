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
   // lateinit var result: TextView

    private lateinit var binding: ActivityManageCommunityBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityManageCommunityBinding.inflate(layoutInflater)


        setContentView(binding.root)

        community = findViewById(R.id.spcommunity) as Spinner
        //result = findViewById(R.id.tv_result) as TextView

        // val communities = arrayOf("Enterprise.Net", "Full-stack Web", "Quality Engineer", "Cloud and DevOps", "Big Data and Analytics",
        // "Product Delivery", "Enterprise Coffee", "Mobile", "Research and Developmen")

//        val spinner : Spinner = findViewById(R.id.spinner)
//        ArrayAdapter.createFromResource(
//            this@ManageCommunityActivity,
//            R.array.community_array,
//            R.layout.custom_simple_spinner_item
//        ).also { adapter ->
//            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
//            spinner.adapter = adapter
//        }
        val adapter = ArrayAdapter.createFromResource(
            this@ManageCommunityActivity,
            R.array.community_array,
            android.R.layout.simple_spinner_item
        )
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spcommunity.adapter = adapter


        with(binding) {
            val spinner = spcommunity.selectedItem.toString()
            button.setOnClickListener{
                val builder: AlertDialog.Builder? = this@ManageCommunityActivity.let {
                    AlertDialog.Builder(it)

                }
                builder?.setTitle("Community Name Saved: ${etNameOfCommunity.text.toString()}")
                    ?.setMessage("Assigned To: $spinner.\n\n\n Description: ${etCommunityDescription.text.toString()}")

                val dialog: AlertDialog? = builder?.create()
                dialog?.show()
            }

            }
        }
    }





