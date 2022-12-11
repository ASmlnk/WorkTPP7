package com.asmlnk.android.asmlnk.worktpp_7

import androidx.lifecycle.ViewModel

class DefectListViewModel: ViewModel() {

    val defects = mutableListOf<Defect>()

    init {
        for (i in 0..20) {
            val defect = Defect()
            defect.title = "Defect #$i"
            defect.logging = i % 2 == 0
            defects += defect
        }

    }

}