package com.example.drone.ui.list

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.drone.model.Drone
import com.example.drone.model.DroneStatus
import com.example.drone.repository.DroneRepository

class DroneListViewModel : ViewModel() {
    private val originalDrones = DroneRepository.getDrones()
    private val _drones = MutableLiveData<List<Drone>>(originalDrones)
    val drones: LiveData<List<Drone>> = _drones

    private val _message = MutableLiveData<String?>(null)
    val message: LiveData<String?> = _message

    private var currentQuery = ""
    private var currentFilter: DroneStatus? = null
    private var currentSortDescending: Boolean? = null

    fun loadDrones() {
        applyFiltersAndSort()
    }

    fun setSearchQuery(query: String) {
        currentQuery = query.trim()
        applyFiltersAndSort()
    }

    fun setFilterStatus(filter: String) {
        currentFilter = when (filter) {
            "Flying" -> DroneStatus.Flying
            "Idle" -> DroneStatus.Idle
            "Charging" -> DroneStatus.Charging
            else -> null
        }
        applyFiltersAndSort()
    }

    fun setSortOption(sort: String) {
        currentSortDescending = when (sort) {
            "High to Low" -> true
            "Low to High" -> false
            else -> null
        }
        applyFiltersAndSort()
    }

    private fun applyFiltersAndSort() {
        val filtered = originalDrones.filter { drone ->
            val matchesSearch = currentQuery.isEmpty() || listOf(
                drone.id,
                drone.name,
                drone.location
            ).any { it.contains(currentQuery, ignoreCase = true) }
            val matchesFilter = currentFilter == null || drone.status == currentFilter
            matchesSearch && matchesFilter
        }

        val sorted = when (currentSortDescending) {
            true -> filtered.sortedByDescending { it.batteryPercentage }
            false -> filtered.sortedBy { it.batteryPercentage }
            else -> filtered
        }

        _drones.value = sorted
        _message.value = if (sorted.isEmpty()) {
            if (originalDrones.isEmpty()) "No drones are configured." else "No results found."
        } else {
            null
        }
    }
}
