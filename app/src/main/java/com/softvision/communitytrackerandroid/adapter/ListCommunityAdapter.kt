package com.softvision.communitytrackerandroid.adapter

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.softvision.communitytrackerandroid.data.model.Community
import com.softvision.communitytrackerandroid.databinding.ItemCommunityBinding
import java.util.*

class ListCommunityAdapter (
        private val dataset: List<Community>,
        private val onItemClick: (position: Int, view:View) -> Unit
        ): RecyclerView.Adapter<ListCommunityAdapter.ListCommunityViewHolder>() {

        class ListCommunityViewHolder(val binding: ItemCommunityBinding, onItemClick: (position: Int, view: View) -> Unit):
                RecyclerView.ViewHolder(binding.root) {
                        init {
                                itemView.setOnClickListener{ view ->
                                        onItemClick(adapterPosition, view)
                                }
        //                            itemView.imbUpdateCommunity({ view ->
        //                            onItemClick(adapterPosition, view)
        //                    }
                        }
                }


        private var isAdmin = false
        public fun  setIsAdmin(isAdmin: Boolean) {
                this.isAdmin = isAdmin
        }
        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListCommunityViewHolder {
                val binding = ItemCommunityBinding.inflate(LayoutInflater.from(parent.context), parent, false)
                return ListCommunityAdapter.ListCommunityViewHolder(binding, onItemClick)
        }

        override fun onBindViewHolder(holder: ListCommunityAdapter.ListCommunityViewHolder, position: Int) {
                val item = dataset[position]
                holder.itemView.apply {
                        val random = Random()
                        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))

                        with(holder.binding) {
                                tvCommunityName.text = item.name
                                layoutColor.setCardBackgroundColor(color)

                                imbUpdateCommunity.setOnClickListener { view ->
                                        onItemClick(position,view)

                                }
                        }
                }
        }

        override fun getItemCount(): Int {
                return dataset.size
        }



}