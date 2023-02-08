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
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.R

class WorkingShiftGeneratorInsulation: Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var generatorInsulationRecycler: RecyclerView
    private lateinit var editStator: EditText
    private lateinit var editRotor: EditText
    private lateinit var editPathogen: EditText
    private lateinit var spinnerTG: Spinner
    private lateinit var textPathogen: TextView
    private lateinit var saveButton: Button

    private lateinit var adapter: ArrayAdapter<CharSequence>
    private var adapterRV: AdapterGeneratorInsulation = AdapterGeneratorInsulation(emptyList())

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
        saveButton = view.findViewById(R.id.button_save)

        adapter = ArrayAdapter.createFromResource(
            requireContext(), R.array.spinner_generator, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

        //val adapter1: ArrayAdapter<String> = ArrayAdapter(this, R.layout.row, R.id.weekofday, R.array.spinner_generator )


        spinnerTG.adapter = adapter

        generatorInsulationRecycler.layoutManager = LinearLayoutManager(context)
        generatorInsulationRecycler.adapter = adapterRV

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        generatorInsulationVM.generatorInsulationLiveData.observe(viewLifecycleOwner) { generatorInsulation ->
            adapterRV.list = generatorInsulation.sortedByDescending { it.date }
            adapterRV.notifyDataSetChanged()
        }
        spinnerTG.onItemSelectedListener = this

        saveButton.setOnClickListener {
            val generatorInsulation = GeneratorInsulation()
            generatorInsulation.name = generatorInsulationVM.textSpinner
            generatorInsulation.stator = editStator.text.toString()
            generatorInsulation.rotor = editRotor.text.toString()
            generatorInsulation.pathogen = editPathogen.text.toString()
            generatorInsulationVM.addGeneratorInsulation(generatorInsulation)
            generatorInsulationRecycler.adapter?.notifyDataSetChanged()
        }
    }

    fun updateUIFalse() {
        editPathogen.text.clear()
        editRotor.text.clear()
        editStator.text.clear()
        textPathogen.isVisible = false
        editPathogen.isVisible = false
    }

    fun updateUITrue() {
        editRotor.text.clear()
        editStator.text.clear()
        editPathogen.text.clear()
        textPathogen.isVisible = true
        editPathogen.isVisible = true
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