package com.softvision.communitytrackerandroid

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import com.softvision.communitytrackerandroid.adapter.ListCommunityMemberAdapter
import com.softvision.communitytrackerandroid.data.DataObject
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ActivityManageCommunityBinding
import com.softvision.communitytrackerandroid.databinding.ActivityResourceBinding
import retrofit2.http.Query

class ResourceActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityResourceBinding
    private lateinit var listCommunityMemberAdapter: ListCommunityMemberAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // TODO Apply community name in to title
        // "$selectedCommunity.name\'s Resource Detail Page"
        listCommunityMemberAdapter = ListCommunityMemberAdapter()

        with(binding) {
            rvListCommunityMember.apply {
                adapter = listCommunityMemberAdapter
                layoutManager = LinearLayoutManager(this@ResourceActivity, LinearLayoutManager.VERTICAL, false)
                setHasFixedSize(true)
            }

            searchView.setOnQueryTextListener(this@ResourceActivity)
            listCommunityMemberAdapter.setData(DataObject.getMemberList())
        }
    }
    override fun onQueryTextSubmit(query: String?): Boolean {
        listCommunityMemberAdapter.filter.filter(query)
        return false
    }

    override fun onQueryTextChange(newText: String?): Boolean {
        listCommunityMemberAdapter.filter.filter(newText)
        return false
    }
}