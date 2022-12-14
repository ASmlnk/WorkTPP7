package com.asmlnk.android.asmlnk.worktpp_7

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import java.util.*

class DefectDetailViewModel(): ViewModel() {
    private val workRepository = WorkRepository.get()
    private val defectIdLiveData = MutableLiveData<UUID>()

    val defectLiveData: LiveData<Defect?> =
        Transformations.switchMap(defectIdLiveData) { defectId ->
            workRepository.getDefect(defectId)
        }

    fun loadDefect(defectId: UUID) {
        defectIdLiveData.value = defectId
    }
}