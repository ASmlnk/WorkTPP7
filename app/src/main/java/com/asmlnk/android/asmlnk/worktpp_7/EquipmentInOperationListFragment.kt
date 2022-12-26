package com.asmlnk.android.asmlnk.worktpp_7

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class EquipmentInOperationListFragment: Fragment() {

    private val equipmentInOperationViewModel: EquipmentInOperationViewModel by lazy {
        ViewModelProvider (this) [EquipmentInOperationViewModel :: class.java]
    }

}