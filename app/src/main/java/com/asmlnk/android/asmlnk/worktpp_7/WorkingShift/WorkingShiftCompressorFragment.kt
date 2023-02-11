package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.R

class WorkingShiftCompressorFragment: Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var compressorRecyclerView: RecyclerView
    private lateinit var editTextVK1: EditText
    private lateinit var editTextVK2: EditText
    private lateinit var editTextVK4: EditText
    private lateinit var editTextVK5: EditText
    private lateinit var editTextVK6: EditText
    private lateinit var spinnerCompressor1: Spinner
    private lateinit var spinnerCompressor2: Spinner
    private lateinit var buttonSave: Button
    private var adapter: AdapterCompressor = AdapterCompressor(emptyList())

    private val workCompressorViewModel: WorkingShiftCompressorViewModel by lazy {
        ViewModelProvider (this) [WorkingShiftCompressorViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.working_shift_compressor_fragment, container, false)

        editTextVK1 = view.findViewById(R.id.vk_1) as EditText
        editTextVK2 = view.findViewById(R.id.vk_2) as EditText
        editTextVK4 = view.findViewById(R.id.vk_4) as EditText
        editTextVK5 = view.findViewById(R.id.vk_5) as EditText
        editTextVK6 = view.findViewById(R.id.vk_6) as EditText
        spinnerCompressor1 = view.findViewById(R.id.spinner_compressors_1) as Spinner
        spinnerCompressor2 = view.findViewById(R.id.spinner_compressors_2) as Spinner
        buttonSave = view.findViewById(R.id.button_save) as Button
        compressorRecyclerView = view.findViewById(R.id.list_compressor) as RecyclerView

        compressorRecyclerView.layoutManager = LinearLayoutManager(context)
        compressorRecyclerView.adapter = adapter

        spinnerCompressor1.adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_genrrator_insulation,
            R.id.text_spinner, workCompressorViewModel.spinnerCompressors1
        )

        spinnerCompressor2.adapter = ArrayAdapter(
            requireContext(),
            R.layout.simple_spinner_dropdown_genrrator_insulation,
            R.id.text_spinner, workCompressorViewModel.spinnerCompressors2
        )

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workCompressorViewModel.compressorListLiveData.observe(viewLifecycleOwner) {compressors ->
           val listCompressors = compressors.sortedByDescending { it.date }
            updateUI(listCompressors)
        }

        spinnerCompressor1.onItemSelectedListener = this
        spinnerCompressor2.onItemSelectedListener = this


        buttonSave.setOnClickListener {
            val compressor = Compressor()
            compressor.vk1 = editTextVK1.text.toString()
            compressor.vk2 = editTextVK2.text.toString()
            compressor.vk4 = editTextVK4.text.toString()
            compressor.vk5 = editTextVK5.text.toString()
            compressor.vk6 = editTextVK6.text.toString()
            compressor.compressors1 = workCompressorViewModel.textSpinnerCompressors1
            compressor.compressors2 = workCompressorViewModel.textSpinnerCompressors2

            workCompressorViewModel.addCompressor(compressor)
            compressorRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
    private fun updateUI(list: List<Compressor>) {
        if (list.isNotEmpty()){
        val editText = list.first()
            editTextVK1.setText(editText.vk1)
            editTextVK2.setText(editText.vk2)
            editTextVK4.setText(editText.vk4)
            editTextVK5.setText(editText.vk5)
            editTextVK6.setText(editText.vk6)
            spinnerCompressor1.setSelection(
                if (editText.compressors1.toIntOrNull() == null) 0 else editText.compressors1.toInt()
            )
            spinnerCompressor2.setSelection(
                if (editText.compressors2.toIntOrNull() == null) 0 else editText.compressors2.toInt()
            )
        }
        adapter.compressors = list
        adapter.notifyDataSetChanged()
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        // val list: Array<String> = arrayOf(resources.getString(R.array.spinner_generator))

        when (parent!!.getItemAtPosition(position)) {
            "ВК-1" -> workCompressorViewModel.textSpinnerCompressors1 = "1"
            "ВК-2" -> workCompressorViewModel.textSpinnerCompressors1 = "2"
            "ВК-4" -> workCompressorViewModel.textSpinnerCompressors2 = "1"
            "ВК-5" -> workCompressorViewModel.textSpinnerCompressors2 = "2"
            "ВК-6" -> workCompressorViewModel.textSpinnerCompressors2 = "3"
        }
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {
    }

    companion object {
        fun newInstance(): WorkingShiftCompressorFragment {
            return WorkingShiftCompressorFragment()
        }
    }
}