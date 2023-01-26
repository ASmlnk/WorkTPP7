package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.asmlnk.android.asmlnk.worktpp_7.R

class WorkingShiftFragment: Fragment() {

    interface Callbacks {
        fun onCompressorSelected()
        fun onControlMeasurementSelected()
    }

    private lateinit var buttonCompressor: Button
    private lateinit var buttonControlMeasurement: Button
    private var callbacks: WorkingShiftFragment.Callbacks? = null

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

        buttonCompressor = view.findViewById(R.id.button_compressor) as Button
        buttonControlMeasurement = view.findViewById(R.id.control_measurement) as Button

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonCompressor.setOnClickListener {
            callbacks?.onCompressorSelected()
        }

        buttonControlMeasurement.setOnClickListener {
            callbacks?.onControlMeasurementSelected()
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