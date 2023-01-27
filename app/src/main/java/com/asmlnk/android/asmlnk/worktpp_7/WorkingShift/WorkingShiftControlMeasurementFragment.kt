package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import android.os.Bundle
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.asmlnk.android.asmlnk.worktpp_7.R
import java.util.zip.Inflater
import kotlin.IllegalArgumentException as IllegalArgumentException1

class WorkingShiftControlMeasurementFragment: Fragment() {

    private lateinit var editPn: EditText
    private lateinit var editPk: EditText
    private lateinit var editTn: EditText
    private lateinit var editTk: EditText
    private lateinit var buttonV: Button
    var pn = 0.0
    var pk = 0.0
    var tn = 0.0
    var tk = 0.0
    var result = "V%"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.working_shift_control_measurement_fragment, container, false)

        editPn = view.findViewById(R.id.edit_pn) as EditText
        editPk = view.findViewById(R.id.edit_pk) as EditText
        editTn = view.findViewById(R.id.edit_tn) as EditText
        editTk = view.findViewById(R.id.edit_tk) as EditText
        buttonV = view.findViewById(R.id.button) as Button

    return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonV.setOnClickListener {

            editPn.text.toString().toDoubleOrNull()?.let { pn = it }
                ?:let {
                    pn = 0.0
                    buttonV.text = "V%" }

            editPk.text.toString().toDoubleOrNull()?.let { pk = it }
                ?:let {
                    pk = 0.0
                    buttonV.text = "V%" }

            editTn.text.toString().toDoubleOrNull()?.let { tn = it }
                ?:let {
                    tn = 0.0
                    buttonV.text = "V%" }

            editTk.text.toString().toDoubleOrNull()?.let { tk = it }
                ?:let {
                    tk = 0.0
                    buttonV.text = "V%" }

            when  {
                pn == 0.0  -> toast("Введите значение Pн \n(давление в начале замера)")
                pk == 0.0  -> toast("Введите значение Pк \n(давление в конце замера)")
                tn == 0.0  -> toast("Введите значение tн \n(температура в начале замера)")
                tk == 0.0  -> toast("Введите значение tк \n(температура в конце замера)")
            else -> {
                val x = 1 - ((pk * (273 + tn)) / (pn * (273 + tk)))
                val v = 100 * kotlin.math.abs(x)
                result = "%.2f".format(v) + "%"
                buttonV.text = result
                toast("V = $result")
                }
            }
        }
    }

    fun toast (text: String) {
        val toast = Toast.makeText(context, text, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    companion object {
        fun newInstance(): WorkingShiftControlMeasurementFragment {
            return WorkingShiftControlMeasurementFragment()
        }
    }
}