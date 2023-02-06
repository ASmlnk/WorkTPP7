package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.R

class WorkingShiftGeneratorInsulation: Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var generatorInsulationRecycler: RecyclerView
    private lateinit var editStator: EditText
    private lateinit var editRotor: EditText
    private lateinit var editPathogen: EditText
    private lateinit var spinnerTG: Spinner
    private lateinit var textPathogen: TextView
    private lateinit var tt: TextView
    private lateinit var adapter: ArrayAdapter<CharSequence>

    private val generatorInsulationVM: WorkingShiftGeneratorInsulationViewModel by lazy {
        ViewModelProvider(this) [WorkingShiftGeneratorInsulationViewModel::class.java]
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.working_shift_generator_insulation_fragment, container, false)

        generatorInsulationRecycler = view.findViewById(R.id.recycler_generator_insulation) as RecyclerView
        editStator = view.findViewById(R.id.edit_stator) as EditText
        editRotor = view.findViewById(R.id.edit_rotor) as EditText
        editPathogen = view.findViewById(R.id.edit_pathogen) as EditText
        spinnerTG = view.findViewById(R.id.spinner_tg) as Spinner
        textPathogen = view.findViewById(R.id.text_pathogen) as TextView
        tt = view.findViewById(R.id.reeeeeeee) as TextView

        adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.spinner_generator, android.R.layout.simple_spinner_item)
        spinnerTG.adapter = adapter


        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        spinnerTG.onItemSelectedListener = this


    }

    fun updateUIFalse() {
        textPathogen.isVisible = false
        editPathogen.isVisible = false
        tt.text = generatorInsulationVM.textSpinner
    }

    fun updateUITrue() {
        textPathogen.isVisible = true
        editPathogen.isVisible = true
        tt.text = generatorInsulationVM.textSpinner
    }

    companion object {
        fun newInstance(): WorkingShiftGeneratorInsulation {
            return WorkingShiftGeneratorInsulation()
        }
    }

    @SuppressLint("ResourceType")
    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
      // val list: Array<String> = arrayOf(resources.getString(R.array.spinner_generator))

        generatorInsulationVM.textSpinner = parent?.getItemAtPosition(position).toString()

        when (parent!!.getItemAtPosition(position)) {
            "ТГ-1" -> updateUITrue()
            "ТГ-3" -> updateUIFalse()
            "ТГ-4" -> updateUITrue()
            "ТГ-5" -> updateUIFalse()
            "ТГ-6" -> updateUIFalse()
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

}