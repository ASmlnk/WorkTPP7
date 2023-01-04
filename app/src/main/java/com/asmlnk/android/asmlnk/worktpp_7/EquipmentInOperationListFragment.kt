package com.asmlnk.android.asmlnk.worktpp_7

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class EquipmentInOperationListFragment: Fragment() {

    private val equipmentInOperationViewModel: EquipmentInOperationViewModel by lazy {
        ViewModelProvider (this) [EquipmentInOperationViewModel :: class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.equipment_work, container, false)

        return view
    }

    companion object {
        fun newInstance(): EquipmentInOperationListFragment {
            return EquipmentInOperationListFragment()
        }
    }


}