package com.asmlnk.android.asmlnk.worktpp_7.WorkingShift

import androidx.lifecycle.ViewModel
import com.asmlnk.android.asmlnk.worktpp_7.Defect.DefectRepository

class WorkingShiftGeneratorInsulationViewModel: ViewModel() {

    var textSpinner = ""

    val generatorInsulationRepository = DefectRepository.get()

    val generatorInsulationLiveData = generatorInsulationRepository.getGeneratorInsulation()

    fun addGeneratorInsulation(generatorInsulation: GeneratorInsulation) {
        generatorInsulationRepository.addGeneratorInsulation(generatorInsulation)
    }

    fun deleteGeneratorInsulation(generatorInsulation: GeneratorInsulation) {
        generatorInsulationRepository.deleteGeneratorInsulation(generatorInsulation)
    }

}