package com.example.drone.model

enum class DroneStatus {
    Flying,
    Idle,
    Charging
}

data class Drone(
    val id: String,
    val name: String,
    val batteryPercentage: Int,
    val status: DroneStatus,
    val location: String,
    val altitude: String = "120 m",
    val speed: String = "25 km/h",
    val lastUpdated: String = "5 minutes ago"
)
