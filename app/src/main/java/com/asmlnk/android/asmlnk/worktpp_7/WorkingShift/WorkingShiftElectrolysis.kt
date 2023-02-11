package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asmlnk.android.asmlnk.worktpp_7.R
import com.asmlnk.android.asmlnk.worktpp_7.databinding.WorkingShiftElectrolysisBinding

class WorkingShiftElectrolysis: Fragment() {

    lateinit var binding: WorkingShiftElectrolysisBinding

    private val workingShiftElectrolysisVM: WorkingShiftElectrolysisViewModel by lazy {
        ViewModelProvider(this) [WorkingShiftElectrolysisViewModel::class.java]
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        binding = WorkingShiftElectrolysisBinding.inflate(inflater, container, false)

        val listVolumeH = workingShiftElectrolysisVM.listVolumeH
        val listVolumeN = workingShiftElectrolysisVM.listVolumeN

        binding.spinnerReceiverH1.adapter = arrayAdapter (listVolumeH)
        binding.spinnerReceiverH2.adapter = arrayAdapter (listVolumeH)
        binding.spinnerReceiverH3.adapter = arrayAdapter (listVolumeH)
        binding.spinnerReceiverH4.adapter = arrayAdapter (listVolumeH)
        binding.spinnerReceiverH5.adapter = arrayAdapter (listVolumeH)
        binding.spinnerReceiverH6.adapter = arrayAdapter (listVolumeH)
        binding.spinnerReceiverN1.adapter = arrayAdapter (listVolumeN)
        binding.spinnerReceiverN2.adapter = arrayAdapter (listVolumeN)
        binding.spinnerReceiverN3.adapter = arrayAdapter (listVolumeN)
        binding.spinnerElectrolysis.adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_genrrator_insulation,
            R.id.text_spinner, workingShiftElectrolysisVM.listElectrolysis)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerReceiverH1.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {

            }
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }
        }

        binding.spinnerReceiverH2.onItemSelectedListener = AdapterSpinner()
    }

    fun arrayAdapter(list: List<Double>): ArrayAdapter<Double> {
        return ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_genrrator_insulation,
            R.id.text_spinner, list
        )
    }

    private inner class AdapterSpinner: AdapterView.OnItemSelectedListener {
        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

        }

        override fun onNothingSelected(p0: AdapterView<*>?) {

        }

    }

    companion object {
        fun newInstance(): WorkingShiftElectrolysis {
            return WorkingShiftElectrolysis()
        }
    }
}