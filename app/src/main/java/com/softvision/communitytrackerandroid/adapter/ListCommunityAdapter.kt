package com.softvision.communitytrackerandroid.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ItemCommunityBinding
import java.util.*

class ListCommunityAdapter (
        private val dataset: List<Community>
        ): RecyclerView.Adapter<ListCommunityAdapter.ListCommunityViewHolder>() {

        class ListCommunityViewHolder(val binding: ItemCommunityBinding): RecyclerView.ViewHolder(binding.root)
        private var isAdmin = false
        public fun  setIsAdmin(isAdmin: Boolean) {
                this.isAdmin = isAdmin
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommunityViewHolder {
                val binding = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ListCommunityViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ListCommunityViewHolder, position: Int) {
                val item = dataset[position]
                holder.itemView.apply {
                        val random = Random()
                        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

                        with(holder.binding) {
                                tvCommunityName.text = item.name
                                layoutColor.setCardBackgroundColor(color)
                        }
                }
        }

        override fun getItemCount(): Int {
                return dataset.size
        }


}