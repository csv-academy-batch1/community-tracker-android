package com.softvision.communitytrackerandroid.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softvision.communitytrackerandroid.data.DataObject
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ItemCommunityBinding
import java.util.*

class ListCommunityAdapter (
        private val dataset: List<Community>,
        private val onItemClick: (position: Int, view: View) -> Unit
        ): RecyclerView.Adapter<ListCommunityAdapter.ListCommunityViewHolder>() {

        class ListCommunityViewHolder(val binding: ItemCommunityBinding, onItemClick: (position: Int, view: View) -> Unit): RecyclerView.ViewHolder(binding.root) {
                init {
                        itemView.setOnClickListener{ view ->
                                onItemClick(adapterPosition, view)
                        }
                }
        }
        private var isAdmin = false
        public fun  setIsAdmin(isAdmin: Boolean) {
                this.isAdmin = isAdmin
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommunityViewHolder {
                val binding = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ListCommunityViewHolder(binding, onItemClick)
        }

        private val colors = DataObject.getColor()
        override fun onBindViewHolder(holder: ListCommunityViewHolder, position: Int) {
                val item = dataset[position]
                holder.itemView.apply {
                        with(holder.binding) {
                                val colorIndex = (position + 1) % colors.size
                                tvCommunityName.text = item.name
                                cvCommunity.setCardBackgroundColor(colors[colorIndex])
                                imbUpdateCommunity.setOnClickListener { view ->
                                        onItemClick(position, view)
                                }

                        }

                }
        }

        override fun getItemCount(): Int {
                return dataset.size
        }


}