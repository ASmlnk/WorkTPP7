package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule.InSc
import com.asmlnk.android.asmlnk.worktpp_7.R

class WorkingShiftFragment: Fragment() {

    interface Callbacks {
        fun onCompressorSelected()
        fun onControlMeasurementSelected()
        fun onInspectionScheduleSelected(workingShift: String)
        fun onGeneratorInsulationSelected()
        fun onElectrolysisSelected()
        fun onEquipmentListSelected()
    }

    private lateinit var buttonEquipment: Button
    private lateinit var buttonCompressor: Button
    private lateinit var buttonControlMeasurement: Button
    private lateinit var buttonDayShift: Button
    private lateinit var buttonNightShift: Button
    private lateinit var buttonGeneratorInsulation: Button
    private lateinit var buttonElectrolysis: Button
    private lateinit var textInspectionSchedule:TextView


    private var callbacks: Callbacks? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.working_shift_fragment, container, false)

        buttonEquipment = view.findViewById(R.id.button_equipment) as Button
        buttonDayShift = view.findViewById(R.id.day_shift) as Button
        buttonNightShift = view.findViewById(R.id.night_shift) as Button
        buttonCompressor = view.findViewById(R.id.button_compressor) as Button
        buttonControlMeasurement = view.findViewById(R.id.control_measurement) as Button
        buttonGeneratorInsulation = view.findViewById(R.id.generator_insulation) as Button
        buttonElectrolysis = view.findViewById(R.id.electrolysis) as Button
        textInspectionSchedule = view.findViewById(R.id.inspection_schedule_text_view) as TextView

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonEquipment.setOnClickListener {
            callbacks?.onEquipmentListSelected()
        }

        buttonCompressor.setOnClickListener {
            callbacks?.onCompressorSelected()
        }

        buttonControlMeasurement.setOnClickListener {
            callbacks?.onControlMeasurementSelected()
        }

        buttonDayShift.setOnClickListener {
            callbacks?.onInspectionScheduleSelected( InSc.DAY.get )
        }

        buttonNightShift.setOnClickListener {
            callbacks?.onInspectionScheduleSelected( InSc.NIGHT.get )
        }

        buttonGeneratorInsulation.setOnClickListener {
            callbacks?.onGeneratorInsulationSelected()
        }

        buttonElectrolysis.setOnClickListener {
            callbacks?.onElectrolysisSelected()
        }

    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance(): WorkingShiftFragment {
            return WorkingShiftFragment()
        }
    }

}