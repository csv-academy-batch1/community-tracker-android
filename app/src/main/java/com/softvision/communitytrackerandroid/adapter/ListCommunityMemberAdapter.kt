package com.softvision.communitytrackerandroid.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.softvision.communitytrackerandroid.data.model.Member
import com.softvision.communitytrackerandroid.databinding.ItemCommunityMemberBinding
import kotlin.collections.ArrayList

class ListCommunityMemberAdapter : RecyclerView.Adapter<ListCommunityMemberAdapter.ListCommunityMemberViewHolder>(),
    Filterable
{
    var membersList: List<Member> = arrayListOf()
    var membersListFiltered: List<Member> = arrayListOf()

    class ListCommunityMemberViewHolder(val binding: ItemCommunityMemberBinding): RecyclerView.ViewHolder(binding.root)

    @SuppressLint("NotifyDataSetChanged")
    fun setData(list: List<Member>) {
        membersList = list
        membersListFiltered = membersList
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommunityMemberViewHolder {
        val binding = ItemCommunityMemberBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListCommunityMemberViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ListCommunityMemberViewHolder, position: Int) {
        val item = membersListFiltered[position]
        holder.itemView.apply {
            with(holder.binding){
                tvName.text = item.firstName
                tvAssignedTo.text = item.assignedTo
                tvHiredDate.text = item.hiredDate
                tvState.text = item.state
                tvJobLevel.text = item.jobLevelId
                tvProject.text = item.project
            }
        }
    }

    override fun getItemCount(): Int {
        return membersListFiltered.size
    }

    override fun getFilter(): Filter {
        return object : Filter() {
            override fun performFiltering(constraint: CharSequence?): FilterResults {
                val charString = constraint?.toString() ?: ""
                if (charString.isEmpty()) membersListFiltered = membersList else {
                    val filteredList = ArrayList<Member>()
                    membersList
                        .filter {
                            (it.firstName.startsWith(constraint!!))
                        }
                        .forEach { filteredList.add(it) }
                    membersListFiltered = filteredList
                }
                return FilterResults().apply { values = membersListFiltered }
            }

            @SuppressLint("NotifyDataSetChanged")
            override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
                membersListFiltered = if (results?.values == null)
                    listOf()
                else
                    results.values as List<Member>
                notifyDataSetChanged()
            }
        }
    }
}