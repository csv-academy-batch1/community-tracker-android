package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.TextView

class ManageCommunityActivity : AppCompatActivity() {

    lateinit var community: Spinner
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_manage_community)

        community = findViewById(R.id.sp_community) as Spinner
        result = findViewById(R.id.tv_result) as TextView

        val communities = arrayOf("Enterprise.Net", "Full-stack Web", "Quality Engineer", "Cloud and DevOps", "Big Data and Analytics",
        "Product Delivery", "Enterprise Coffee", "Mobile", "Research and Developmen")

        community.adapter = ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,communities)

        community.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                result.text = "Please Select on Option"
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
                 result.text = communities.get(position)
            }

        }
    }
}



