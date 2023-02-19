package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel

class WorkingShiftElectrolysisViewModel: ViewModel() {

   private val electrolysisMutableLiveData = MutableLiveData<Electrolysis>()

    var electrolysisLiveData: LiveData<Electrolysis> = electrolysisMutableLiveData

    val listVolumeH = listOf(0.0, 4.0, 4.5, 5.0, 5.5, 6.0, 6.5, 7.0, 7.5, 8.0)
    val listVolumeN = listOf(0.0, 4.0, 4.5, 5.0, 5.5, 6.0)
    val listElectrolysis = listOf("", "1", "2")

    var configuration = 2

    var h1 = 0
    var h2 = 0
    var h3 = 0
    var h4 = 0
    var h5 = 0
    var h6 = 0

    fun addLiveData (electrolysis: Electrolysis) {
        electrolysisMutableLiveData.value = electrolysis
    }

}