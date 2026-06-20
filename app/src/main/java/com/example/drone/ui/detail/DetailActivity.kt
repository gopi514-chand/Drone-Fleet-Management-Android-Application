package com.example.drone.ui.detail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.drone.databinding.ActivityDetailBinding
import com.example.drone.model.Drone
import com.example.drone.repository.DroneRepository

class DetailActivity : AppCompatActivity() {
    companion object {
        const val EXTRA_DRONE_ID = "extra_drone_id"
    }

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val droneId = intent.getStringExtra(EXTRA_DRONE_ID)
        val drone = droneId?.let { DroneRepository.getDroneById(it) }

        if (drone == null) {
            binding.detailMessage.text = "Drone details are unavailable."
            binding.detailMessage.visibility = android.view.View.VISIBLE
            binding.droneDetailGroup.visibility = android.view.View.GONE
            return
        }

        binding.droneDetailGroup.visibility = android.view.View.VISIBLE
        binding.detailMessage.visibility = android.view.View.GONE

        bindDrone(drone)
    }

    private fun bindDrone(drone: Drone) {
        binding.detailDroneId.text = drone.id
        binding.detailDroneName.text = drone.name
        binding.detailDroneLocation.text = drone.location
        binding.detailDroneStatus.text = drone.status.name
        binding.detailDroneBattery.text = "${drone.batteryPercentage}%"
        binding.detailAltitude.text = drone.altitude
        binding.detailSpeed.text = drone.speed
        binding.detailLastUpdated.text = drone.lastUpdated
        binding.detailBatteryProgress.progress = drone.batteryPercentage

        val stateText = when {
            drone.batteryPercentage > 50 -> "Healthy"
            drone.batteryPercentage in 20..50 -> "Warning"
            else -> "Critical"
        }
        binding.detailBatteryState.text = stateText
    }
}
