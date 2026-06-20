package com.example.drone.repository

import com.example.drone.model.Drone
import com.example.drone.model.DroneStatus

object DroneRepository {
    private val droneList = listOf(
        Drone("GA-001", "Survey Drone", 85, DroneStatus.Flying, "Mysore"),
        Drone("GA-002", "Spray Drone", 42, DroneStatus.Idle, "Mandya"),
        Drone("GA-003", "Mapping Drone", 18, DroneStatus.Charging, "Bangalore"),
        Drone("GA-004", "Inspection Drone", 67, DroneStatus.Flying, "Tumkur"),
        Drone("GA-005", "Agriculture Drone", 95, DroneStatus.Idle, "Hassan")
    )

    fun getDrones(): List<Drone> = droneList

    fun getDroneById(id: String): Drone? = droneList.firstOrNull { it.id == id }
}
