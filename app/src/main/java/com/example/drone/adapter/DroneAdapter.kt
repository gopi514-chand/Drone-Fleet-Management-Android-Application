package com.example.drone.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.drone.databinding.ItemDroneBinding
import com.example.drone.model.Drone
import com.example.drone.model.DroneStatus

class DroneAdapter(
    private var items: List<Drone> = emptyList(),
    private val onClick: (Drone) -> Unit
) : RecyclerView.Adapter<DroneAdapter.DroneViewHolder>() {

    fun submitList(list: List<Drone>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DroneViewHolder {
        val binding = ItemDroneBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return DroneViewHolder(binding)
    }

    override fun onBindViewHolder(holder: DroneViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class DroneViewHolder(private val binding: ItemDroneBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(drone: Drone) {
            binding.droneId.text = drone.id
            binding.droneName.text = drone.name
            binding.droneLocation.text = drone.location
            binding.droneStatus.text = drone.status.name
            binding.droneBatteryText.text = "${drone.batteryPercentage}%"
            binding.droneBatteryProgress.progress = drone.batteryPercentage
            binding.root.setOnClickListener { onClick(drone) }

            when (batteryState(drone.batteryPercentage)) {
                BatteryState.Healthy -> {
                    binding.batteryStateLabel.text = "Healthy"
                    binding.batteryStateLabel.setTextColor(binding.root.context.getColor(android.R.color.holo_green_dark))
                }
                BatteryState.Warning -> {
                    binding.batteryStateLabel.text = "Warning"
                    binding.batteryStateLabel.setTextColor(binding.root.context.getColor(android.R.color.holo_orange_dark))
                }
                BatteryState.Critical -> {
                    binding.batteryStateLabel.text = "Critical"
                    binding.batteryStateLabel.setTextColor(binding.root.context.getColor(android.R.color.holo_red_dark))
                }
            }

            binding.statusChip.text = drone.status.name
        }

        private fun batteryState(value: Int): BatteryState {
            return when {
                value > 50 -> BatteryState.Healthy
                value in 20..50 -> BatteryState.Warning
                else -> BatteryState.Critical
            }
        }
    }

    private enum class BatteryState {
        Healthy,
        Warning,
        Critical
    }
}
