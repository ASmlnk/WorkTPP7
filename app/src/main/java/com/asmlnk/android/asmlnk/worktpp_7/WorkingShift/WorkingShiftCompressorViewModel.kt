package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import androidx.lifecycle.ViewModel
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectRepository

class WorkingShiftCompressorViewModel: ViewModel() {

    val spinnerCompressors1 = listOf("", "ВК-1", "ВК-2")
    val spinnerCompressors2 = listOf("", "ВК-4", "ВК-5", "ВК-6")

    var textSpinnerCompressors1 = ""
    var textSpinnerCompressors2 = ""

    val compressorRepository = DefectRepository.get()
    val compressorListLiveData = compressorRepository.getCompressors()

    fun addCompressor(compressor: Compressor) {
        compressorRepository.addCompressor(compressor)
    }

    fun deleteCompressor(compressor: Compressor) {
        compressorRepository.deleteCompressor(compressor)
    }


}