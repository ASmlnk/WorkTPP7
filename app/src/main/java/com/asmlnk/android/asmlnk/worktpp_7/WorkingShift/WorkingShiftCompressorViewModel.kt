package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import androidx.lifecycle.ViewModel
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectRepository

class WorkingShiftCompressorViewModel: ViewModel() {

    val compressorRepository = DefectRepository.get()
    val compressorListLiveData = compressorRepository.getCompressors()

    fun addCompressor(compressor: Compressor) {
        compressorRepository.addCompressor(compressor)
    }

    fun deleteCompressor(compressor: Compressor) {
        compressorRepository.deleteCompressor(compressor)
    }


}