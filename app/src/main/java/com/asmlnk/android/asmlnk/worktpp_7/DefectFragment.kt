package com.asmlnk.android.asmlnk.worktpp_7

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.EditText
import android.widget.ImageButton
import android.widget.ImageView
import androidx.fragment.app.Fragment

class DefectFragment: Fragment() {

    private lateinit var defect: Defect
    private lateinit var photoDefect: ImageView
    private lateinit var selectCamera: ImageButton
    private lateinit var defectTitle: EditText
    private lateinit var defectDetails: EditText
    private  lateinit var loggingCheckBox: CheckBox
    private lateinit var fixedDefectCheckBox: CheckBox

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_defect, container, false)

        photoDefect = view.findViewById(R.id.photo_defect) as ImageView
        selectCamera = view.findViewById(R.id.select_camera) as ImageButton
        defectTitle = view.findViewById(R.id.defect_title) as EditText
        defectDetails = view.findViewById(R.id.defect_details) as EditText
        loggingCheckBox = view.findViewById(R.id.check_box_logging) as CheckBox
        fixedDefectCheckBox = view.findViewById(R.id.check_box_defect_fixed) as CheckBox

        loggingCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                defect.logging = isChecked
            }
        }
        fixedDefectCheckBox.apply {
            setOnCheckedChangeListener { _, isChecked ->
                defect.defectFixed = isChecked
            }
        }

        return view
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        defect = Defect()
    }

    override fun onStart() {
        super.onStart()

        val titleWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                defect.title = s.toString()
            }
            override fun afterTextChanged(s: Editable?) {
            }
        }
        val detailsWatcher = object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                defect.details = s.toString()

            }
            override fun afterTextChanged(s: Editable?) {
            }
        }

        defectTitle.addTextChangedListener(titleWatcher)
        defectDetails.addTextChangedListener(detailsWatcher)

    }

}

