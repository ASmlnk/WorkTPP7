package com.asmlnk.android.asmlnk.worktpp_7

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView

class AdapterEquipmentCategory(val list: List<EquipmentInOperationViewModel.EquipmentCategory>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (list[viewType].name) {
            "К/А-6", "К/А-7", "К/А-8", "К/А-9" -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_pager_boler_unit, parent, false)
                EquipmentHolderBoiler(view)
            }
            "ТГ-1", "ТГ-3", "ТГ-4", "ТГ-5", "ТГ-6" -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_pager_turbogenerator, parent, false)
                EquipmentHolderTurbogenerator(view)
            }
            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_pager_turbogenerator, parent, false)
                EquipmentHolderTurbogenerator(view)
            }
        }


    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val listEquipmentBoiler = list[position].listElectricMotor
        val mapEquipmentTurbogenerator = list[position].mapElectricMotor

        when (holder) {
            is EquipmentHolderBoiler -> {
                holder.bind(listEquipmentBoiler)
            }
            is EquipmentHolderTurbogenerator -> {
                holder.bind(mapEquipmentTurbogenerator)
            }
        }
    }

    override fun getItemCount() = list.size

    inner class EquipmentHolderBoiler(view: View) : RecyclerView.ViewHolder(view) {

        val checkBox1: CheckBox = itemView.findViewById(R.id.electric_motor_1)
        val checkBox2: CheckBox = itemView.findViewById(R.id.electric_motor_2)
        val checkBox3: CheckBox = itemView.findViewById(R.id.electric_motor_3)
        val checkBox4: CheckBox = itemView.findViewById(R.id.electric_motor_4)
        val checkBox5: CheckBox = itemView.findViewById(R.id.electric_motor_5)
        val checkBox6: CheckBox = itemView.findViewById(R.id.electric_motor_6)
        val checkBox7: CheckBox = itemView.findViewById(R.id.electric_motor_7)
        val collectedAll: TextView = itemView.findViewById(R.id.electric_motor_collected_all)
        val dismantledAll: TextView = itemView.findViewById(R.id.electric_motor_dismantled_all)

        fun checkBox(checkBox: CheckBox, it: Int, list: List<ElectricMotor>) {
            checkBox.apply {
                setOnCheckedChangeListener { _, isChecked ->
                    list[it].schemaState = isChecked
                }
                text = list[it].name
            }
        }

        fun bind(listElectricMotor: List<ElectricMotor>) {

            checkBox(checkBox1, 0, listElectricMotor)
            checkBox(checkBox2, 1, listElectricMotor)
            checkBox(checkBox3, 2, listElectricMotor)
            checkBox(checkBox4, 3, listElectricMotor)
            checkBox(checkBox5, 4, listElectricMotor)
            checkBox(checkBox6, 5, listElectricMotor)
            checkBox(checkBox7, 6, listElectricMotor)

            collectedAll.setOnClickListener {
                checkBox1.isChecked = true
                checkBox2.isChecked = true
                checkBox3.isChecked = true
                checkBox4.isChecked = true
                checkBox5.isChecked = true
                checkBox6.isChecked = true
                checkBox7.isChecked = true
            }

            dismantledAll.setOnClickListener {
                checkBox1.isChecked = false
                checkBox2.isChecked = false
                checkBox3.isChecked = false
                checkBox4.isChecked = false
                checkBox5.isChecked = false
                checkBox6.isChecked = false
                checkBox7.isChecked = false
            }
        }

    }

    inner class EquipmentHolderTurbogenerator(view: View) : RecyclerView.ViewHolder(view) {

        val checkBoxGonA: CheckBox = itemView.findViewById(R.id.electric_motor_gon_a)
        val checkBoxGonB: CheckBox = itemView.findViewById(R.id.electric_motor_gon_b)
        val checkBoxPmn: CheckBox = itemView.findViewById(R.id.electric_motor_pmn)
        val checkBoxVpu: CheckBox = itemView.findViewById(R.id.electric_motor_vpu)
        val checkBoxMns: CheckBox = itemView.findViewById(R.id.electric_motor_mns)
        val checkBoxMnsPost: CheckBox = itemView.findViewById(R.id.electric_motor_mns_post)
        val checkBoxMnuPost: CheckBox = itemView.findViewById(R.id.electric_motor_mnu_post)
        val checkBoxMnuA: CheckBox = itemView.findViewById(R.id.electric_motor_mnu_a)
        val checkBoxMnuB: CheckBox = itemView.findViewById(R.id.electric_motor_mnu_b)
        val checkBoxKnbA: CheckBox = itemView.findViewById(R.id.electric_motor_knb_a)
        val checkBoxKnbB: CheckBox = itemView.findViewById(R.id.electric_motor_knb_b)
        val checkBoxKnbV: CheckBox = itemView.findViewById(R.id.electric_motor_knb_v)
        val checkBoxKnA: CheckBox = itemView.findViewById(R.id.electric_motor_kn_1)
        val checkBoxKnB: CheckBox = itemView.findViewById(R.id.electric_motor_kn_2)
        val checkBoxKnV: CheckBox = itemView.findViewById(R.id.electric_motor_kn_3)
        val checkBoxSlnA: CheckBox = itemView.findViewById(R.id.electric_motor_sln_a)
        val checkBoxSlnB: CheckBox = itemView.findViewById(R.id.electric_motor_sln_b)
        val collectedAll: TextView = itemView.findViewById(R.id.electric_motor_collected_all)
        val dismantledAll: TextView = itemView.findViewById(R.id.electric_motor_dismantled_all)

        fun checkBox (checkBox: CheckBox, key: String, map: Map<String, ElectricMotor>) {
            if (map.containsKey(key)) {
                checkBox.apply {
                    setOnCheckedChangeListener { _, isChecked ->
                        map.getValue(key).schemaState = isChecked
                    }
                    text = map.getValue(key).name
                }
            } else {
                checkBox.isVisible = false
            }
        }

        fun bind(mapElectricMotor: Map<String, ElectricMotor>) {

            checkBox(checkBoxGonA, "ГОН-А", mapElectricMotor)
            checkBox(checkBoxGonB, "ГОН-Б", mapElectricMotor)
            checkBox(checkBoxPmn, "ПМН", mapElectricMotor)
            checkBox(checkBoxVpu, "ВПУ", mapElectricMotor)
            checkBox(checkBoxMns, "МНС", mapElectricMotor)
            checkBox(checkBoxMnsPost, "МНС=", mapElectricMotor)
            checkBox(checkBoxMnuPost, "МНУ=", mapElectricMotor)
            checkBox(checkBoxMnuA, "МНУ-А", mapElectricMotor)
            checkBox(checkBoxMnuB, "МНУ-Б", mapElectricMotor)
            checkBox(checkBoxKnbA, "КНБ-А", mapElectricMotor)
            checkBox(checkBoxKnbB, "КНБ-Б", mapElectricMotor)
            checkBox(checkBoxKnbV, "КНБ-В", mapElectricMotor)
            checkBox(checkBoxKnA, "КН-А", mapElectricMotor)
            checkBox(checkBoxKnB, "КН-Б", mapElectricMotor)
            checkBox(checkBoxKnV, "КН-В", mapElectricMotor)
            checkBox(checkBoxSlnA, "СлН-А", mapElectricMotor)
            checkBox(checkBoxSlnB, "СлН-Б", mapElectricMotor)






        }



    }
}

