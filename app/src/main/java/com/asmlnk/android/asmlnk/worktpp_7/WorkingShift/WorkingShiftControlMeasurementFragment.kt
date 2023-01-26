package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import com.asmlnk.android.asmlnk.worktpp_7.R
import java.util.zip.Inflater

class WorkingShiftControlMeasurementFragment: Fragment() {
    private lateinit var editPn: EditText
    private lateinit var editPk: EditText
    private lateinit var editTn: EditText
    private lateinit var editTk: EditText
    private lateinit var buttonV: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.working_shift_control_measurement_fragment, container, false)

    return view
    }




    companion object {
        fun newInstance(): WorkingShiftControlMeasurementFragment {
            return WorkingShiftControlMeasurementFragment()
        }
    }
}