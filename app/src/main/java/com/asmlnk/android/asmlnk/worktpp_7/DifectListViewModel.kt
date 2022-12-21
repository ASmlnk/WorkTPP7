package com.asmlnk.android.asmlnk.worktpp_7

import androidx.lifecycle.ViewModel

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
