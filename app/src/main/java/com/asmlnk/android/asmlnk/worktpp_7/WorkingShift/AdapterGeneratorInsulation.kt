package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectRepository
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.EM
import com.asmlnk.android.asmlnk.worktpp_7.R
import java.text.DateFormat

class AdapterGeneratorInsulation(var list: List<GeneratorInsulation>):
    RecyclerView.Adapter<RecyclerView.ViewHolder>(),
    View.OnLongClickListener {

    private inner class GeneratorInsulationHolderPathogen(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.apply {

                setOnLongClickListener {
                    DefectRepository.get().deleteGeneratorInsulation(list[position])
                    return@setOnLongClickListener true
                }
            }
        }

        val nameText: TextView = view.findViewById(R.id.textView17)
        val dataText: TextView = view.findViewById(R.id.text_data)
        val statorText: TextView = view.findViewById(R.id.list_text_froze_stator)
        val rotorText: TextView = view.findViewById(R.id.list_text_froze_rotor)
        val pathogen: TextView = view.findViewById(R.id.list_text_froze_pathogen)

        fun bind(generatorInsulation: GeneratorInsulation) {
            val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
                .format(generatorInsulation.date)
            dataText.text = dateFormat.toString()
            nameText.text = generatorInsulation.name

            statorText.text = itemView.context.getString(R.string.MOm, generatorInsulation.stator)
            rotorText.text = itemView.context.getString(R.string.MOm, generatorInsulation.rotor)
            pathogen.text = itemView.context.getString(R.string.MOm, generatorInsulation.pathogen)
        }
    }

    private inner class GeneratorInsulationHolder(view: View) : RecyclerView.ViewHolder(view) {

        init {
            itemView.apply {

                setOnLongClickListener {
                    DefectRepository.get().deleteGeneratorInsulation(list[position])
                    return@setOnLongClickListener true
                }
            }
        }

        val nameText: TextView = view.findViewById(R.id.textView17)
        val dataText: TextView = view.findViewById(R.id.text_data)
        val statorText: TextView = view.findViewById(R.id.list_text_froze_stator)
        val rotorText: TextView = view.findViewById(R.id.list_text_froze_rotor)

        fun bind(generatorInsulation: GeneratorInsulation) {
            val dateFormat = DateFormat.getDateInstance(DateFormat.MEDIUM)
                .format(generatorInsulation.date)
            dataText.text = dateFormat.toString()
            nameText.text = generatorInsulation.name

            statorText.text = itemView.context.getString(R.string.MOm, generatorInsulation.stator)
            rotorText.text = itemView.context.getString(R.string.MOm, generatorInsulation.rotor)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == 0) {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_generator_insulation_pathogen, parent, false)
                    GeneratorInsulationHolderPathogen(view)
                } else  {
                    val view = LayoutInflater.from(parent.context)
                        .inflate(R.layout.list_item_generator_insulation, parent, false)
                    GeneratorInsulationHolder(view)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val generatorInsulation = list[position]
       when (holder) {
           is GeneratorInsulationHolderPathogen ->  holder.bind(generatorInsulation)
           is GeneratorInsulationHolder -> holder.bind(generatorInsulation)
       }
    }

    override fun getItemCount(): Int = list.size

    override fun getItemViewType(position: Int): Int {
        return when (list[position].name) {
            EM.TG_1.кеуElectricMotor, EM.TG_4.кеуElectricMotor -> 0
            else -> 1
        }
    }

    override fun onLongClick(v: View?): Boolean {
        return true
    }
}