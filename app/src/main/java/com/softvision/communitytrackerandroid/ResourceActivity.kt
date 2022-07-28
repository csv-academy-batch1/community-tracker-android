package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.softvision.communitytrackerandroid.adapter.ListCommunityMemberAdapter
import com.softvision.communitytrackerandroid.data.DataObject
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding
import com.softvision.communitytrackerandroid.databinding.ActivityResourceBinding

class ResourceActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResourceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO Apply community name in to title
        // "$selectedCommunity.name\'s Resource Detail Page"

        with(binding) {
            rvListCommunityMember.apply {
                adapter = ListCommunityMemberAdapter(DataObject.getMemberList())
                layoutManager = LinearLayoutManager(this@ResourceActivity, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }
        }
    }
}