package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.fragment.app.Fragment
import com.asmlnk.android.asmlnk.worktpp_7.databinding.WorkingShiftElectrolysisBinding

class WorkingShiftElectrolysis: Fragment() {

    lateinit var binding: WorkingShiftElectrolysisBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = WorkingShiftElectrolysisBinding.inflate(inflater, container, false)

        return binding.root
    }
    companion object {
        fun newInstance(): WorkingShiftElectrolysis {
            return WorkingShiftElectrolysis()
        }
    }
}