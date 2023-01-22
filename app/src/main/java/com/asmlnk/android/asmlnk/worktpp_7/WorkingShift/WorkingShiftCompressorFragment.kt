package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
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

        return view
    }


    companion object {
        fun newInstance(): WorkingShiftCompressorFragment {
            return WorkingShiftCompressorFragment()
        }
    }
}