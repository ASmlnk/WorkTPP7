package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork

import android.app.AlertDialog
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.EM
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.data.FirestoreElectricMotorRepository
import com.asmlnk.android.asmlnk.worktpp_7.R

class AdapterEquipmentCategory(val list: List<EquipmentInOperationViewModel.EquipmentCategory>)
    : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return when (list[viewType].name) {
            EM.KA_6.кеуElectricMotor,
            EM.KA_7.кеуElectricMotor,
            EM.KA_8.кеуElectricMotor,
            EM.KA_9.кеуElectricMotor -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_pager_boler_unit, parent, false)
                EquipmentHolderBoiler(view)
            }

            EM.TG_1.кеуElectricMotor,
            EM.TG_3.кеуElectricMotor,
            EM.TG_4.кеуElectricMotor,
            EM.TG_5.кеуElectricMotor,
            EM.TG_6.кеуElectricMotor,-> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_pager_turbogenerator, parent, false)
                EquipmentHolderTurbogenerator(view)
            }

            else -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.view_pager_other_equipment, parent, false)
                EquipmentHolderOther(view)
            }
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {

        val listEquipment = list[position].listElectricMotor

        when (holder) {
            is EquipmentHolderBoiler -> {
                holder.bind(listEquipment)
            }
            is EquipmentHolderTurbogenerator -> {
                holder.bind(listEquipment)
            }
            is EquipmentHolderOther -> {
                holder.bind(listEquipment)
            }
        }
    }

    override fun getItemCount() = list.size

    private inner class EquipmentHolderBoiler(view: View) : RecyclerView.ViewHolder(view), View.OnLongClickListener {

        val checkBoxDa: CheckBox = itemView.findViewById(R.id.electric_motor_r1s1)
        val checkBoxDb: CheckBox = itemView.findViewById(R.id.electric_motor_r1s2)
        val checkBoxDvA1: CheckBox = itemView.findViewById(R.id.electric_motor_r1s3)
        val checkBoxDvA2: CheckBox = itemView.findViewById(R.id.electric_motor_r1s4)
        val checkBoxDvb1: CheckBox = itemView.findViewById(R.id.electric_motor_r1s5)
        val checkBoxDvb2: CheckBox = itemView.findViewById(R.id.electric_motor_r1s6)
        val checkBoxVgdn: CheckBox = itemView.findViewById(R.id.electric_motor_r1s7)
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
                                EM.CAT_BOILER_UNIT.кеуElectricMotor,
                                isChecked)
                    }

                    setOnLongClickListener {_ ->
                        val myDialog = AlertDialog.Builder(itemView.context)
                        val text = electricMotor.characteristics
                            .replace("\\n", System.getProperty("line.separator"))
                        myDialog
                            .setTitle(electricMotor.name)
                            .setMessage(text).create().show()
                         return@setOnLongClickListener true
                    }
                    text = electricMotor.name
                    isChecked = electricMotor.schemaState
                }
            }

            checkBox(checkBoxDa, EM.D_A.кеуElectricMotor)
            checkBox(checkBoxDb, EM.D_B.кеуElectricMotor)
            checkBox(checkBoxDvA1, EM.DV_A1.кеуElectricMotor)
            checkBox(checkBoxDvA2, EM.DV_A2.кеуElectricMotor)
            checkBox(checkBoxDvb1, EM.DV_B1.кеуElectricMotor)
            checkBox(checkBoxDvb2, EM.DV_B2.кеуElectricMotor)
            checkBox(checkBoxVgdn, EM.VGDN.кеуElectricMotor)

            collectedAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = true }
            }
            dismantledAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = false }
            }
        }

        override fun onLongClick(v: View?): Boolean {
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

        fun bind(listElectricMotor: List<ElectricMotor>) {

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

            fun checkBox(checkBox: CheckBox, keyCheckBox: String) {
                val list = listElectricMotor.filter { it.keyCheckBox == keyCheckBox }
                if (list.isEmpty()) {
                    checkBox.isVisible = false
                } else {

                    val electricMotor = list.first()
                    checkBox.apply {

                        setOnCheckedChangeListener { _, isChecked ->
                            FirestoreElectricMotorRepository()
                                .addSchemaState(
                                    electricMotor,
                                    EM.CAT_TURBOGENERATOR.кеуElectricMotor,
                                    isChecked)
                        }

                        setOnLongClickListener { _ ->
                            val myDialog = AlertDialog.Builder(itemView.context)
                            val text = electricMotor.characteristics
                                .replace("\\n", System.getProperty("line.separator"))
                            myDialog
                                .setTitle(electricMotor.name)
                                .setMessage(text).create().show()
                            return@setOnLongClickListener true
                        }
                        text = electricMotor.name
                        isChecked = electricMotor.schemaState
                    }
                }
            }
            checkBox(checkBoxGonA, EM.GON_A.кеуElectricMotor)
            checkBox(checkBoxGonB, EM.GON_B.кеуElectricMotor)
            checkBox(checkBoxPmn, EM.PMN.кеуElectricMotor)
            checkBox(checkBoxVpu, EM.VPY.кеуElectricMotor)
            checkBox(checkBoxMns, EM.MNS.кеуElectricMotor)
            checkBox(checkBoxMnsPost, EM.MNS_P.кеуElectricMotor)
            checkBox(checkBoxMnuPost, EM.MNY_P.кеуElectricMotor)
            checkBox(checkBoxMnuA, EM.MNY_A.кеуElectricMotor)
            checkBox(checkBoxMnuB, EM.MNY_B.кеуElectricMotor)
            checkBox(checkBoxKnbA, EM.KNB_A.кеуElectricMotor)
            checkBox(checkBoxKnbB, EM.KNB_B.кеуElectricMotor)
            checkBox(checkBoxKnbV, EM.KNB_V.кеуElectricMotor)
            checkBox(checkBoxKnA, EM.KN_A.кеуElectricMotor)
            checkBox(checkBoxKnB, EM.KN_B.кеуElectricMotor)
            checkBox(checkBoxKnV, EM.KN_V.кеуElectricMotor)
            checkBox(checkBoxSlnA, EM.SLN_A.кеуElectricMotor)
            checkBox(checkBoxSlnB, EM.SLN_B.кеуElectricMotor)

            collectedAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = true }
            }
            dismantledAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = false }
            }
        }
    }

    private inner class EquipmentHolderOther(view: View) : RecyclerView.ViewHolder(view) {

        val checkBoxR1S1: CheckBox = view.findViewById(R.id.electric_motor_r1s1)
        val checkBoxR1S2: CheckBox = view.findViewById(R.id.electric_motor_r1s2)
        val checkBoxR1S3: CheckBox = view.findViewById(R.id.electric_motor_r1s3)
        val checkBoxR1S4: CheckBox = view.findViewById(R.id.electric_motor_r1s4)
        val checkBoxR1S5: CheckBox = view.findViewById(R.id.electric_motor_r1s5)
        val checkBoxR1S6: CheckBox = view.findViewById(R.id.electric_motor_r1s6)
        val checkBoxR1S7: CheckBox = view.findViewById(R.id.electric_motor_r1s7)
        val checkBoxR2S1: CheckBox = view.findViewById(R.id.electric_motor_r2s1)
        val checkBoxR2S2: CheckBox = view.findViewById(R.id.electric_motor_r2s2)
        val checkBoxR2S3: CheckBox = view.findViewById(R.id.electric_motor_r2s3)
        val checkBoxR2S4: CheckBox = view.findViewById(R.id.electric_motor_r2s4)
        val checkBoxR2S5: CheckBox = view.findViewById(R.id.electric_motor_r2s5)
        val checkBoxR2S6: CheckBox = view.findViewById(R.id.electric_motor_r2s6)
        val checkBoxR2S7: CheckBox = view.findViewById(R.id.electric_motor_r2s7)
        val checkBoxR3S1: CheckBox = view.findViewById(R.id.electric_motor_r3s1)
        val checkBoxR3S2: CheckBox = view.findViewById(R.id.electric_motor_r3s2)
        val checkBoxR3S3: CheckBox = view.findViewById(R.id.electric_motor_r3s3)
        val checkBoxR3S4: CheckBox = view.findViewById(R.id.electric_motor_r3s4)
        val checkBoxR3S5: CheckBox = view.findViewById(R.id.electric_motor_r3s5)
        val checkBoxR3S6: CheckBox = view.findViewById(R.id.electric_motor_r3s6)
        val checkBoxR3S7: CheckBox = view.findViewById(R.id.electric_motor_r3s7)
        val collectedAll: TextView = itemView.findViewById(R.id.electric_motor_collected_all2)
        val dismantledAll: TextView = itemView.findViewById(R.id.electric_motor_dismantled_all2)

        fun bind(listElectricMotor: List<ElectricMotor>) {

            val listCheckBox = listOf(
                checkBoxR1S1,
                checkBoxR1S2,
                checkBoxR1S3,
                checkBoxR1S4,
                checkBoxR1S5,
                checkBoxR1S6,
                checkBoxR1S7,
                checkBoxR2S1,
                checkBoxR2S2,
                checkBoxR2S3,
                checkBoxR2S4,
                checkBoxR2S5,
                checkBoxR2S6,
                checkBoxR2S7,
                checkBoxR3S1,
                checkBoxR3S2,
                checkBoxR3S3,
                checkBoxR3S4,
                checkBoxR3S5,
                checkBoxR3S6,
                checkBoxR3S7)

            fun checkBox(checkBox: CheckBox, keyCheckBox: String) {
                val list = listElectricMotor.filter { it.keyCheckBox == keyCheckBox }
                if (list.isEmpty()) {
                    checkBox.isVisible = false
                } else {

                    val electricMotor = list.first()
                    checkBox.apply {

                        setOnCheckedChangeListener { _, isChecked ->
                            FirestoreElectricMotorRepository()
                                .addSchemaState(
                                    electricMotor,
                                    EM.CAT_OTHER.кеуElectricMotor,
                                    isChecked)
                        }

                        setOnLongClickListener { _ ->
                            val myDialog = AlertDialog.Builder(itemView.context)
                            val text = electricMotor.characteristics
                                .replace("\\n", System.getProperty("line.separator"))
                            myDialog
                                .setTitle(electricMotor.name)
                                .setMessage(text).create().show()
                            return@setOnLongClickListener true
                        }
                        text = electricMotor.name
                        isChecked = electricMotor.schemaState
                    }
                }
            }
            checkBox (checkBoxR1S1, EM.R1_S1.кеуElectricMotor)
            checkBox (checkBoxR1S2, EM.R1_S2.кеуElectricMotor)
            checkBox (checkBoxR1S3, EM.R1_S3.кеуElectricMotor)
            checkBox (checkBoxR1S4, EM.R1_S4.кеуElectricMotor)
            checkBox (checkBoxR1S5, EM.R1_S5.кеуElectricMotor)
            checkBox (checkBoxR1S6, EM.R1_S6.кеуElectricMotor)
            checkBox (checkBoxR1S7, EM.R1_S7.кеуElectricMotor)
            checkBox (checkBoxR2S1, EM.R2_S1.кеуElectricMotor)
            checkBox (checkBoxR2S2, EM.R2_S2.кеуElectricMotor)
            checkBox (checkBoxR2S3, EM.R2_S3.кеуElectricMotor)
            checkBox (checkBoxR2S4, EM.R2_S4.кеуElectricMotor)
            checkBox (checkBoxR2S5, EM.R2_S5.кеуElectricMotor)
            checkBox (checkBoxR2S6, EM.R2_S6.кеуElectricMotor)
            checkBox (checkBoxR2S7, EM.R2_S7.кеуElectricMotor)
            checkBox (checkBoxR3S1, EM.R3_S1.кеуElectricMotor)
            checkBox (checkBoxR3S2, EM.R3_S2.кеуElectricMotor)
            checkBox (checkBoxR3S3, EM.R3_S3.кеуElectricMotor)
            checkBox (checkBoxR3S4, EM.R3_S4.кеуElectricMotor)
            checkBox (checkBoxR3S5, EM.R3_S5.кеуElectricMotor)
            checkBox (checkBoxR3S6, EM.R3_S6.кеуElectricMotor)
            checkBox (checkBoxR3S7, EM.R3_S7.кеуElectricMotor)

            collectedAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = true }
            }
            dismantledAll.setOnClickListener {
                listCheckBox.forEach { it.isChecked = false }
            }
        }
    }
}

