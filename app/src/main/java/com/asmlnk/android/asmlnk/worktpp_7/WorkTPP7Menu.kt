package com.asmlnk.android.asmlnk.worktpp_7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.ElectricMotor
import com.asmlnk.android.asmlnk.worktpp_7.dataFirestore.FirestoreRepository
import com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule.Inspection

class WorkTPP7Menu: Fragment() {

    interface Callbacks {
        fun onDefectListSelected()
        fun onEquipmentListSelected()
        fun onWorkingShift()
        //fun onInspectionScheduleSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var defects: Button
    private lateinit var equipmentAtWork: Button
    private lateinit var buttonElectricMotor: Button
    private lateinit var buttonWorkingShift: Button
    private lateinit var buttonInspectionSchedule: Button

    private val firebaseRepository: FirestoreRepository = FirestoreRepository()
    private val em: ElectricMotor = ElectricMotor()
    private val inspection  = Inspection()

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.center_list_menu, container, false)

        defects = view.findViewById(R.id.defects) as Button
        equipmentAtWork = view.findViewById(R.id.equipment_work) as Button
        buttonElectricMotor = view.findViewById(R.id.button_em) as Button
        buttonWorkingShift = view.findViewById(R.id.working_shift) as Button
        buttonInspectionSchedule = view.findViewById(R.id.inspection_schedule) as Button

        defects.setOnClickListener {
            callbacks?.onDefectListSelected()
        }

        equipmentAtWork.setOnClickListener {
            callbacks?.onEquipmentListSelected()
        }

        buttonWorkingShift.setOnClickListener {
            callbacks?.onWorkingShift()
        }

        buttonInspectionSchedule.setOnClickListener {
           // callbacks?.onInspectionScheduleSelected()
        }
        buttonInspectionSchedule.isVisible = false

        buttonElectricMotor.setOnClickListener {
            //firebaseRepository.addElectricMotor(em)
            firebaseRepository.addInspection(inspection)

        }
        buttonElectricMotor.isVisible = false

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance(): WorkTPP7Menu {
            return WorkTPP7Menu()
        }
    }

}