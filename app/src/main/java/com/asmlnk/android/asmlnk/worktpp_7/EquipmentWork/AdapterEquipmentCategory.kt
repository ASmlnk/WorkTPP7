package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import android.widget.Toast
import androidx.core.graphics.red
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.R

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
                    .inflate(R.layout.view_pager_boler_unit, parent, false)
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

    inner class EquipmentHolderBoiler(view: View) : RecyclerView.ViewHolder(view), View.OnLongClickListener {

        val checkBox1: CheckBox = itemView.findViewById(R.id.electric_motor_1)
        val checkBox2: CheckBox = itemView.findViewById(R.id.electric_motor_2)
        val checkBox3: CheckBox = itemView.findViewById(R.id.electric_motor_3)
        val checkBox4: CheckBox = itemView.findViewById(R.id.electric_motor_4)
        val checkBox5: CheckBox = itemView.findViewById(R.id.electric_motor_5)
        val checkBox6: CheckBox = itemView.findViewById(R.id.electric_motor_6)
        val checkBox7: CheckBox = itemView.findViewById(R.id.electric_motor_7)
        val collectedAll: TextView = itemView.findViewById(R.id.electric_motor_collected_all)
        val dismantledAll: TextView = itemView.findViewById(R.id.electric_motor_dismantled_all)

        init {
            checkBox1.setOnLongClickListener(this)
        }

        fun bind(listElectricMotor: List<ElectricMotor>) {

            fun checkBox(checkBox: CheckBox, it: Int) {
                checkBox.apply {
                    setOnCheckedChangeListener { _, isChecked ->
                        listElectricMotor[it].schemaState = isChecked
                    }
                    text = listElectricMotor[it].name
                }
            }

            checkBox(checkBox1, 0)
            checkBox(checkBox2, 1)
            checkBox(checkBox3, 2)
            checkBox(checkBox4, 3)
            checkBox(checkBox5, 4)
            checkBox(checkBox6, 5)
            checkBox(checkBox7, 6)




            val listCheckBox = listOf(checkBox1,checkBox2,checkBox3,checkBox4,checkBox5,checkBox6,checkBox7)

            collectedAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = true }
            }

            dismantledAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = false }
            }
        }


        override fun onLongClick(v: View?): Boolean {
            val myDialog = AlertDialog.Builder(itemView.context)
            myDialog
                .setMessage("P=35fh\nS=sjd\njsdhfh").create().show()

            return true
        }
    }

    private inner class EquipmentHolderTurbogenerator(view: View) : RecyclerView.ViewHolder(view) {

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



        fun bind(mapElectricMotor: Map<String, ElectricMotor>) {

            fun checkBox (checkBox: CheckBox, key: String) {
                if (mapElectricMotor.containsKey(key)) {
                    checkBox.apply {
                        setOnCheckedChangeListener { _, isChecked ->
                            mapElectricMotor.getValue(key).schemaState = isChecked
                        }
                        text = mapElectricMotor.getValue(key).name
                    }
                } else {
                    checkBox.isVisible = false
                }
            }

            checkBox(checkBoxGonA, "ГОН-А")
            checkBox(checkBoxGonB, "ГОН-Б")
            checkBox(checkBoxPmn, "ПМН")
            checkBox(checkBoxVpu, "ВПУ")
            checkBox(checkBoxMns, "МНС")
            checkBox(checkBoxMnsPost, "МНС=")
            checkBox(checkBoxMnuPost, "МНУ=")
            checkBox(checkBoxMnuA, "МНУ-А")
            checkBox(checkBoxMnuB, "МНУ-Б")
            checkBox(checkBoxKnbA, "КНБ-А")
            checkBox(checkBoxKnbB, "КНБ-Б")
            checkBox(checkBoxKnbV, "КНБ-В")
            checkBox(checkBoxKnA, "КН-А")
            checkBox(checkBoxKnB, "КН-Б")
            checkBox(checkBoxKnV, "КН-В")
            checkBox(checkBoxSlnA, "СлН-А")
            checkBox(checkBoxSlnB, "СлН-Б")

            val listCheckBox = listOf(checkBoxGonA,
                    checkBoxGonB,
                    checkBoxPmn,
                    checkBoxVpu,
                    checkBoxMns,
                    checkBoxMnsPost,
                    checkBoxMnuPost,
                    checkBoxMnuA,
                    checkBoxMnuB,
                    checkBoxKnbA,
                    checkBoxKnbB,
                    checkBoxKnbV,
                    checkBoxKnA,
                    checkBoxKnB,
                    checkBoxKnV,
                    checkBoxSlnA,
                    checkBoxSlnB)

            collectedAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = true }
            }
            dismantledAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = false }
            }


        }
    }
}

