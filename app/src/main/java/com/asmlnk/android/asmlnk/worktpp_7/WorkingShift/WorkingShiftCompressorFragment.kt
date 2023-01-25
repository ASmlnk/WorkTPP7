package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.asmlnk.android.asmlnk.worktpp_7.R

class WorkingShiftCompressorFragment: Fragment() {

    private lateinit var compressorRecyclerView: RecyclerView
    private lateinit var editTextVK1: EditText
    private lateinit var editTextVK2: EditText
    private lateinit var editTextVK4: EditText
    private lateinit var editTextVK5: EditText
    private lateinit var editTextVK6: EditText
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
        buttonSave = view.findViewById(R.id.button_save) as Button
        compressorRecyclerView = view.findViewById(R.id.list_compressor) as RecyclerView

        compressorRecyclerView.layoutManager = LinearLayoutManager(context)
        compressorRecyclerView.adapter = adapter

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        workCompressorViewModel.compressorListLiveData.observe(viewLifecycleOwner) {compressors ->
           val listCompressors = compressors.sortedByDescending { it.date }
            updateUI(listCompressors)
        }

        buttonSave.setOnClickListener {
            val compressor = Compressor()
            compressor.vk1 = editTextVK1.text.toString()
            compressor.vk2 = editTextVK2.text.toString()
            compressor.vk4 = editTextVK4.text.toString()
            compressor.vk5 = editTextVK5.text.toString()
            compressor.vk6 = editTextVK6.text.toString()
            workCompressorViewModel.addCompressor(compressor)
            compressorRecyclerView.adapter?.notifyDataSetChanged()
        }
    }
    private fun updateUI(list: List<Compressor>) {
        val editText = list.first()
        editTextVK1.setText(editText.vk1)
        editTextVK2.setText(editText.vk2)
        editTextVK4.setText(editText.vk4)
        editTextVK5.setText(editText.vk5)
        editTextVK6.setText(editText.vk6)
        adapter.compressors = list
        adapter.notifyDataSetChanged()
    }


    companion object {
        fun newInstance(): WorkingShiftCompressorFragment {
            return WorkingShiftCompressorFragment()
        }
    }
}