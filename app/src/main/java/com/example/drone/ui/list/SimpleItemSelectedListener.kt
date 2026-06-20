package com.example.drone.ui.list

import android.view.View
import android.widget.AdapterView

class SimpleItemSelectedListener(
    private val onItemSelected: (String) -> Unit
) : AdapterView.OnItemSelectedListener {
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        val item = parent?.getItemAtPosition(position)?.toString() ?: ""
        onItemSelected(item)
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }
}
