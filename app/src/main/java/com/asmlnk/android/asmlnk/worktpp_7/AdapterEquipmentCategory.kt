package com.asmlnk.android.asmlnk.worktpp_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.zip.Inflater

class AdapterEquipmentCategory(val list: List<String>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class EquipmentHolder (view: View) : RecyclerView.ViewHolder(view) {
        val titleTextView: TextView = view.findViewById(R.id.name_category_equipment)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_equipment_work, parent, false)
        return EquipmentHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is EquipmentHolder) {
            holder.titleTextView.text = list[position]
        }
    }

    override fun getItemCount(): Int = list.size


}