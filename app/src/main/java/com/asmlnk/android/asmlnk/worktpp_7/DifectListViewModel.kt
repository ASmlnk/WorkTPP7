package com.asmlnk.android.asmlnk.worktpp_7

import androidx.lifecycle.ViewModel

class DefectListViewModel: ViewModel() {

    private val workRepository = WorkRepository.get()
    val defectListLiveData = workRepository.getDefects()

}