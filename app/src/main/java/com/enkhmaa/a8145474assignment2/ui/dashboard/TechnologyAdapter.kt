package com.enkhmaa.a8145474assignment2.ui.dashboard

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.enkhmaa.a8145474assignment2.data.Technology
import com.enkhmaa.a8145474assignment2.databinding.ItemTechnologyBinding

class TechnologyAdapter(
    private val onItemClick: (Technology) -> Unit
) : ListAdapter<Technology, TechnologyAdapter.TechnologyViewHolder>(DiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TechnologyViewHolder {
        val binding = ItemTechnologyBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return TechnologyViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TechnologyViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    inner class TechnologyViewHolder(
        private val binding: ItemTechnologyBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(technology: Technology) {
            binding.deviceNameText.text = technology.deviceName
            binding.manufacturerText.text = technology.manufacturer
            binding.osText.text = technology.operatingSystem
            binding.releaseYearText.text = technology.releaseYear.toString()

            binding.root.setOnClickListener {
                onItemClick(technology)
            }
        }
    }

    companion object DiffCallback : DiffUtil.ItemCallback<Technology>() {
        override fun areItemsTheSame(oldItem: Technology, newItem: Technology): Boolean {
            return oldItem.deviceName == newItem.deviceName
        }

        override fun areContentsTheSame(oldItem: Technology, newItem: Technology): Boolean {
            return oldItem == newItem
        }
    }
}
