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
        private val onItemClick: (position: Int, view: View) -> Unit
        ): RecyclerView.Adapter<ListCommunityAdapter.ListCommunityViewHolder>() {


        class ListCommunityViewHolder(val binding: ItemCommunityBinding, onItemClick: (position: Int, view: View) -> Unit): RecyclerView.ViewHolder(binding.root) {
                init {
                    itemView.setOnClickListener { view ->
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

        override fun onBindViewHolder(holder: ListCommunityViewHolder, position: Int) {
                val item = dataset[position]
                holder.itemView.apply {
                        val random = Random()
                        val color = Color.argb(255, random.nextInt(256), random.nextInt(256), random.nextInt(256))
                        val colors = arrayOf(
                                Color.parseColor("#ffc581"),
                                Color.parseColor("#ffa382"),
                                Color.parseColor("#ff8a84"),
                                Color.parseColor("#f06e9c"),
                                Color.parseColor("#d756f6"),
                                Color.parseColor("#8b51f5"),
                                Color.parseColor("#aedd94"),
                                Color.parseColor("#75a9f9"),
                                Color.parseColor("#4dd7fa")
                        )
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