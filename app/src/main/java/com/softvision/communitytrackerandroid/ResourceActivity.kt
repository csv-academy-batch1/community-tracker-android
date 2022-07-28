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

class ResourceActivity : AppCompatActivity(), SearchView.OnQueryTextListener {

    private lateinit var binding: ActivityResourceBinding
    private lateinit var listCommunityMemberAdapter: ListCommunityMemberAdapter
    private var action: Int = MainActivity.ACTION_VIEW_COMMUNITY
    private var selectedCommunity: Community? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityResourceBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // TODO Apply community name in to title
        // "$selectedCommunity.name\'s Resource Detail Page"

        val bundle = intent.extras
        if (bundle != null) {
            action = bundle.getInt("action", MainActivity.ACTION_VIEW_COMMUNITY)
            selectedCommunity = bundle.getParcelable<Community>("community") ?: null
        }

        listCommunityMemberAdapter = ListCommunityMemberAdapter()


            with(binding) {

               tvTitle.setText("${selectedCommunity?.name} Resource Details Page.")
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