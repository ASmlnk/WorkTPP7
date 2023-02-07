package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectRepository
import com.asmlnk.android.asmlnk.worktpp_7.R
import java.text.DateFormat

class AdapterCompressor(var compressors: List<Compressor>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    View.OnLongClickListener,
    View.OnClickListener {

    private inner class CompressorHolder(view: View): RecyclerView.ViewHolder(view) {

        private lateinit var compressor: Compressor

        init {
            itemView.apply {
                setOnLongClickListener {
                    DefectRepository.get().deleteCompressor(compressors[position])
                    return@setOnLongClickListener true
                }
                setOnClickListener {
                    val myDialog = AlertDialog.Builder(itemView.context)
                    val compressor = compressors[position]
                    val text = "ВК-1 = ${compressor.vk1}\n\n" +
                            "ВК-2 = ${compressor.vk2}\n\n" +
                            "ВК-4 = ${compressor.vk4}\n\n" +
                            "ВК-5 = ${compressor.vk5}\n\n" +
                            "ВК-6 = ${compressor.vk6}\n\n"
                    myDialog
                        .setMessage(text).create().show()
                }
            }
        }

        val dateText: TextView = view.findViewById(R.id.date_compressor)

        fun bind(compressor: Compressor) {
            val dateFormat = DateFormat.getDateTimeInstance(DateFormat.MEDIUM, DateFormat.MEDIUM).format(compressor.date)
            dateText.text = dateFormat.toString()
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item_compressor, parent, false)
        return CompressorHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val compressor = compressors[position]
        holder as CompressorHolder
        holder.bind(compressor)
    }

    override fun getItemCount() = compressors.size

    override fun onLongClick(v: View?): Boolean {
        return true
    }

    override fun onClick(v: View?) {
    }

}