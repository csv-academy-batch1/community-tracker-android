package com.softvision.communitytrackerandroid.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.data.model.Member
import com.softvision.communitytrackerandroid.databinding.ItemCommunityBinding
import com.softvision.communitytrackerandroid.databinding.ItemCommunityMemberBinding

class ListCommunityMemberAdapter (
    private val dataset: List<Member>
        ): RecyclerView.Adapter<ListCommunityMemberAdapter.ListCommunityMemberViewHolder>() {

    class ListCommunityMemberViewHolder(val binding: ItemCommunityMemberBinding): RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommunityMemberViewHolder {
        val binding = ItemCommunityMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCommunityMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCommunityMemberViewHolder, position: Int) {
        val item = dataset[position]
        holder.itemView.apply {
            with(holder.binding){
                tvName.text = item.firstName
                tvAssignedTo.text = item.assignedTo
                tvHiredDate.text = item.hiredDate
            }
        }
    }

    override fun getItemCount(): Int {
        return dataset.size
    }

}