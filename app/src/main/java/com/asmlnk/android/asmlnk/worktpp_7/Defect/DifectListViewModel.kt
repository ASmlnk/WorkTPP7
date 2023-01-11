package com.asmlnk.android.asmlnk.worktpp_7

import androidx.lifecycle.ViewModel
import com.asmlnk.android.asmlnk.worktpp_7.Defect.Defect
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectRepository

class DefectListViewModel: ViewModel() {

    private val defectRepository = DefectRepository.get()
    val defectListLiveData = defectRepository.getDefects()

    fun addDefect(defect: Defect) {
        defectRepository.addDefect(defect)
    }

    fun deleteDefect(defect: Defect) {
        defectRepository.deleteDefect(defect)
    }
}

/*
ViewModel для DefectListFragment*/
