package com.example.drone.ui.list

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.ArrayAdapter
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.drone.R
import com.example.drone.adapter.DroneAdapter
import com.example.drone.databinding.ActivityMainBinding
import com.example.drone.model.Drone
import com.example.drone.ui.detail.DetailActivity

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private lateinit var viewModel: DroneListViewModel
    private lateinit var adapter: DroneAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[DroneListViewModel::class.java]
        adapter = DroneAdapter(emptyList()) { openDetail(it) }

        binding.droneRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.droneRecyclerView.adapter = adapter

        binding.searchInput.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                viewModel.setSearchQuery(s.toString())
            }
            override fun afterTextChanged(s: Editable?) {}
        })

        val filterAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("All", "Flying", "Idle", "Charging")
        )
        filterAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.filterSpinner.adapter = filterAdapter
        binding.filterSpinner.setSelection(0)
        binding.filterSpinner.onItemSelectedListener = SimpleItemSelectedListener { selected ->
            viewModel.setFilterStatus(selected)
        }

        val sortAdapter = ArrayAdapter(
            this,
            android.R.layout.simple_spinner_item,
            listOf("None", "High to Low", "Low to High")
        )
        sortAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.sortSpinner.adapter = sortAdapter
        binding.sortSpinner.setSelection(0)
        binding.sortSpinner.onItemSelectedListener = SimpleItemSelectedListener { selected ->
            viewModel.setSortOption(selected)
        }

        binding.swipeRefresh.setOnRefreshListener {
            viewModel.loadDrones()
            binding.swipeRefresh.isRefreshing = false
        }

        viewModel.drones.observe(this) { list ->
            adapter.submitList(list)
        }
        viewModel.message.observe(this) { message ->
            binding.messageText.text = message ?: ""
            binding.messageText.visibility = if (message == null) View.GONE else View.VISIBLE
        }

        viewModel.loadDrones()
    }

    private fun openDetail(drone: Drone) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra(DetailActivity.EXTRA_DRONE_ID, drone.id)
        startActivity(intent)
    }
}
