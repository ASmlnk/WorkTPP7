package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asmlnk.android.asmlnk.worktpp_7.R
import com.asmlnk.android.asmlnk.worktpp_7.dataFirestore.FirestoreRepository
import com.asmlnk.android.asmlnk.worktpp_7.databinding.WorkingShiftElectrolysisBinding
import com.google.firebase.firestore.ktx.toObject

class WorkingShiftElectrolysis: Fragment() {

    lateinit var binding: WorkingShiftElectrolysisBinding
    val firestoreRepository = FirestoreRepository()

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

        firestore()
        updateButtonConfig()

        workingShiftElectrolysisVM.electrolysisLiveData.observe(viewLifecycleOwner) { electrolysis ->
            binding.progressBar2.isVisible = false
            updateSpinners(electrolysis)
            updateTextView()
        }

        binding.configuration1.setOnClickListener {
            workingShiftElectrolysisVM.configuration = 1
            updateTextView()
            binding.configuration2.setBackgroundResource(R.drawable.ic_baseline_looks_two_24_bw)
            binding.configuration1.setBackgroundResource(R.drawable.ic_baseline_looks_one_24)
        }

        binding.configuration2.setOnClickListener {
            workingShiftElectrolysisVM.configuration = 2
            updateTextView()
            binding.configuration2.setBackgroundResource(R.drawable.ic_baseline_looks_two_24)
            binding.configuration1.setBackgroundResource(R.drawable.ic_baseline_looks_one_24_bw)
        }
    }

    override fun onPause() {
        super.onPause()
        addFirestore()
    }

    fun firestore() {

        firestoreRepository.getElectrolysis().addOnSuccessListener {
            val electrolysis = it.toObject<Electrolysis>()
            workingShiftElectrolysisVM.addLiveData(electrolysis!!)
        }
    }

    fun updateUI(n: Double, oneVolume: Int, textVolume: TextView) {
        val volumeAll = (n*oneVolume).toInt()
        val volumeGenerator = if (workingShiftElectrolysisVM.configuration == 1)  50 else 68
        val volumeRemainder = (volumeAll - volumeGenerator )
        val volumeRemainderText = if (volumeRemainder < 0) 0 else volumeRemainder
        textVolume.text = context?.getString(R.string.receiver_volume, oneVolume.toString(), volumeAll.toString(), volumeRemainderText.toString())
        textVolume.isVisible = true

        when (textVolume) {
            binding.volumeReceiverH1 -> workingShiftElectrolysisVM.h1 = volumeRemainderText
            binding.volumeReceiverH2 -> workingShiftElectrolysisVM.h2 = volumeRemainderText
            binding.volumeReceiverH3 -> workingShiftElectrolysisVM.h3 = volumeRemainderText
            binding.volumeReceiverH4 -> workingShiftElectrolysisVM.h4 = volumeRemainderText
            binding.volumeReceiverH5 -> workingShiftElectrolysisVM.h5 = volumeRemainderText
            binding.volumeReceiverH6 -> workingShiftElectrolysisVM.h6 = volumeRemainderText
        }

        val listReceiverH = listOf(
            workingShiftElectrolysisVM.h1,
            workingShiftElectrolysisVM.h2,
            workingShiftElectrolysisVM.h3,
            workingShiftElectrolysisVM.h4,
            workingShiftElectrolysisVM.h5,
            workingShiftElectrolysisVM.h6)
        val sumReceiverH = listReceiverH.sumBy { it }
        binding.textVolumeGenerator.text = context?.getString(R.string.volume_generator, sumReceiverH.toString())

    }

    fun updateTextView() {

        val positionReceiverH1 = binding.spinnerReceiverH1.selectedItem.toString().toDouble()
        updateUI(positionReceiverH1, 20, binding.volumeReceiverH1)

        val positionReceiverH2 = binding.spinnerReceiverH2.selectedItem.toString().toDouble()
        updateUI(positionReceiverH2, 20, binding.volumeReceiverH2)

        val positionReceiverH3 = binding.spinnerReceiverH3.selectedItem.toString().toDouble()
        updateUI(positionReceiverH3, 20, binding.volumeReceiverH3)

        val positionReceiverH4 = binding.spinnerReceiverH4.selectedItem.toString().toDouble()
        updateUI(positionReceiverH4, 20, binding.volumeReceiverH4)

        val positionReceiverH5 = binding.spinnerReceiverH5.selectedItem.toString().toDouble()
        updateUI(positionReceiverH5, 16, binding.volumeReceiverH5)

        val positionReceiverH6 = binding.spinnerReceiverH6.selectedItem.toString().toDouble()
        updateUI(positionReceiverH6, 16, binding.volumeReceiverH6)

        val positionReceiverN1 = binding.spinnerReceiverN1.selectedItem.toString().toDouble()
        updateUI(positionReceiverN1, 20, binding.volumeReceiverN1)

        val positionReceiverN2 = binding.spinnerReceiverN2.selectedItem.toString().toDouble()
        updateUI(positionReceiverN2, 20, binding.volumeReceiverN2)

        val positionReceiverN3 = binding.spinnerReceiverN3.selectedItem.toString().toDouble()
        updateUI(positionReceiverN3, 20, binding.volumeReceiverN3)

    }

    fun updateButtonConfig() {

        when (workingShiftElectrolysisVM.configuration) {
            1 -> {
                binding.configuration1.setBackgroundResource(R.drawable.ic_baseline_looks_one_24)
                binding.configuration2.setBackgroundResource(R.drawable.ic_baseline_looks_two_24_bw) }
            2 -> {
                binding.configuration1.setBackgroundResource(R.drawable.ic_baseline_looks_one_24_bw)
                binding.configuration2.setBackgroundResource(R.drawable.ic_baseline_looks_two_24) }
        }
    }

    fun updateSpinners (electrolysis: Electrolysis) {

        binding.spinnerReceiverH1.setSelection(electrolysis.h2_1)
        binding.spinnerReceiverH2.setSelection(electrolysis.h2_2)
        binding.spinnerReceiverH3.setSelection(electrolysis.h2_3)
        binding.spinnerReceiverH4.setSelection(electrolysis.h2_4)
        binding.spinnerReceiverH5.setSelection(electrolysis.h2_5)
        binding.spinnerReceiverH6.setSelection(electrolysis.h2_6)
        binding.spinnerReceiverN1.setSelection(electrolysis.n2_1)
        binding.spinnerReceiverN2.setSelection(electrolysis.n2_2)
        binding.spinnerReceiverN3.setSelection(electrolysis.n2_3)
        binding.spinnerElectrolysis.setSelection(electrolysis.electrolysis)

        binding.spinnerReceiverH1.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverH1, 20)
        binding.spinnerReceiverH2.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverH2, 20)
        binding.spinnerReceiverH3.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverH3, 20)
        binding.spinnerReceiverH4.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverH4, 20)
        binding.spinnerReceiverH5.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverH5, 16)
        binding.spinnerReceiverH6.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverH6, 16)
        binding.spinnerReceiverN1.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverN1, 20)
        binding.spinnerReceiverN2.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverN2, 20)
        binding.spinnerReceiverN3.onItemSelectedListener = AdapterSpinner(binding.volumeReceiverN3, 20)


    }

    fun arrayAdapter (list: List<Double>): ArrayAdapter<Double> {
        return ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_genrrator_insulation,
            R.id.text_spinner, list
        )
    }

    private inner class AdapterSpinner(val textVolume: TextView, val oneVolume: Int): AdapterView.OnItemSelectedListener {

        override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
            val n = p0?.getItemAtPosition(p2) as Double
            updateUI(n, oneVolume, textVolume)
        }

        override fun onNothingSelected(p0: AdapterView<*>?) {
        }
    }

    fun addFirestore() {

        val electrolysis = Electrolysis()
        electrolysis.h2_1 = binding.spinnerReceiverH1.selectedItemPosition
        electrolysis.h2_2 = binding.spinnerReceiverH2.selectedItemPosition
        electrolysis.h2_3 = binding.spinnerReceiverH3.selectedItemPosition
        electrolysis.h2_4 = binding.spinnerReceiverH4.selectedItemPosition
        electrolysis.h2_5 = binding.spinnerReceiverH5.selectedItemPosition
        electrolysis.h2_6 = binding.spinnerReceiverH6.selectedItemPosition
        electrolysis.n2_1 = binding.spinnerReceiverN1.selectedItemPosition
        electrolysis.n2_2 = binding.spinnerReceiverN2.selectedItemPosition
        electrolysis.n2_3 = binding.spinnerReceiverN3.selectedItemPosition
        electrolysis.electrolysis = binding.spinnerElectrolysis.selectedItemPosition
        firestoreRepository.addElectrolysis(electrolysis)
    }

    companion object {
        fun newInstance(): WorkingShiftElectrolysis {
            return WorkingShiftElectrolysis()
        }
    }
}