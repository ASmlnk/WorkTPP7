package com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.R

class AdapterInspectionSchedule (val list: MutableList<Inspection>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {

        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_inspection, parent, false)

        return InspectionHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val inspection = list[position]
        holder as InspectionHolder
        holder.bind(inspection)
    }

    override fun getItemCount() = list.size

    private inner class InspectionHolder(view: View) : RecyclerView.ViewHolder(view) {

        val textContentInspection: TextView = itemView.findViewById(R.id.text_content_inspector)
        val textTimeSpending: TextView = itemView.findViewById(R.id.text_timeSpending_inspector)

        fun bind(inspection: Inspection) {
            textContentInspection.text = inspection.content
            textTimeSpending.text = inspection.timeSpending
        }
    }
}