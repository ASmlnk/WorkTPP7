package com.asmlnk.android.asmlnk.worktpp_7

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import java.util.*

class WorkTPP7Menu: Fragment() {

    interface Callbacks {
        fun onDefectListSelected()
        fun onEquipmentListSelected()
    }

    private var callbacks: Callbacks? = null
    private lateinit var defects: Button
    private lateinit var equipmentAtWork: Button

    override fun onAttach(context: Context) {
        super.onAttach(context)
        callbacks = context as Callbacks?
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.center_list_menu, container, false)

        defects = view.findViewById(R.id.defects) as Button
        equipmentAtWork = view.findViewById(R.id.equipment_work) as Button

        defects.setOnClickListener {
            callbacks?.onDefectListSelected()
        }

        equipmentAtWork.setOnClickListener {
            callbacks?.onEquipmentListSelected()
        }

        return view
    }

    override fun onDetach() {
        super.onDetach()
        callbacks = null
    }

    companion object {
        fun newInstance(): WorkTPP7Menu {
            return WorkTPP7Menu()
        }
    }

}