package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.ELECTRICMOTOR
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.data.FirestoreElectricMotorRepository
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

        val checkBoxDa: CheckBox = itemView.findViewById(R.id.electric_motor_1)
        val checkBoxDb: CheckBox = itemView.findViewById(R.id.electric_motor_2)
        val checkBoxDvA1: CheckBox = itemView.findViewById(R.id.electric_motor_3)
        val checkBoxDvA2: CheckBox = itemView.findViewById(R.id.electric_motor_4)
        val checkBoxDvb1: CheckBox = itemView.findViewById(R.id.electric_motor_5)
        val checkBoxDvb2: CheckBox = itemView.findViewById(R.id.electric_motor_6)
        val checkBoxVgdn: CheckBox = itemView.findViewById(R.id.electric_motor_7)
        val collectedAll: TextView = itemView.findViewById(R.id.electric_motor_collected_all)
        val dismantledAll: TextView = itemView.findViewById(R.id.electric_motor_dismantled_all)

        val listCheckBox = listOf(checkBoxDa,checkBoxDb,checkBoxDvA1,checkBoxDvA2,checkBoxDvb1,checkBoxDvb2,checkBoxVgdn)

        init {
            listCheckBox.forEach { it.setOnLongClickListener(this) }

        }

        fun bind(listElectricMotor: List<ElectricMotor>) {

            fun checkBox(checkBox: CheckBox, keyCheckBox: String) {
                val electricMotor = listElectricMotor.filter { it.keyCheckBox == keyCheckBox }.first()
                checkBox.apply {

                    setOnCheckedChangeListener { _, isChecked ->
                        FirestoreElectricMotorRepository()
                            .addSchemaState(
                                electricMotor,
                                ELECTRICMOTOR.CAT_BOILER_UNIT.кеуElectricMotor,
                                isChecked)
                    }

                    setOnLongClickListener {_ ->
                        val myDialog = AlertDialog.Builder(itemView.context)
                        myDialog
                            .setMessage(electricMotor.characteristics).create().show()
                         return@setOnLongClickListener true
                    }

                    text = electricMotor.name
                    isChecked = electricMotor.schemaState
                }

            }

            checkBox(checkBoxDa, ELECTRICMOTOR.D_A.кеуElectricMotor)
            checkBox(checkBoxDb, ELECTRICMOTOR.D_B.кеуElectricMotor)
            checkBox(checkBoxDvA1, ELECTRICMOTOR.DV_A1.кеуElectricMotor)
            checkBox(checkBoxDvA2, ELECTRICMOTOR.DV_A2.кеуElectricMotor)
            checkBox(checkBoxDvb1, ELECTRICMOTOR.DV_B1.кеуElectricMotor)
            checkBox(checkBoxDvb2, ELECTRICMOTOR.DV_B2.кеуElectricMotor)
            checkBox(checkBoxVgdn, ELECTRICMOTOR.VGDN.кеуElectricMotor)




            collectedAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = true }
            }

            dismantledAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = false }
            }
        }




        override fun onLongClick(v: View?): Boolean {
            /*val myDialog = AlertDialog.Builder(itemView.context)
            myDialog
                .setMessage("P=35fh\nS=sjd\njsdhfh").create().show()*/

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

