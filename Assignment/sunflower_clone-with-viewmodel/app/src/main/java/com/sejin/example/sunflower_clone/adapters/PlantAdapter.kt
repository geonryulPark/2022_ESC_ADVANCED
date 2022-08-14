package com.sejin.example.sunflower_clone.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.sejin.example.sunflower_clone.HomeViewPagerFragmentDirections
import com.sejin.example.sunflower_clone.data.Plant
import com.sejin.example.sunflower_clone.databinding.ListItemPlantBinding

class PlantAdapter : ListAdapter<Plant, RecyclerView.ViewHolder>(PlantDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return PlantViewHolder(
            ListItemPlantBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val plant = getItem(position)
        (holder as PlantViewHolder).bind(plant)
    }

    class PlantViewHolder(private val binding: ListItemPlantBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(item: Plant) {

            binding.apply {
                root.setOnClickListener { navigateToPlant(item, this.root) }
                plantItemImage.load(item.imageUrl)
                plantItemTitle.text = item.name
            }
        }

        private fun navigateToPlant(plant: Plant, view: View) {
            val direction =
                HomeViewPagerFragmentDirections.actionHomeViewPagerFragmentToPlantDetailFragment(
                    id = plant.plantId
                )
            view.findNavController().navigate(direction)
        }
    }
}

private class PlantDiffCallback : DiffUtil.ItemCallback<Plant>() {
    override fun areItemsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem.plantId == newItem.plantId
    }

    override fun areContentsTheSame(oldItem: Plant, newItem: Plant): Boolean {
        return oldItem == newItem
    }
}