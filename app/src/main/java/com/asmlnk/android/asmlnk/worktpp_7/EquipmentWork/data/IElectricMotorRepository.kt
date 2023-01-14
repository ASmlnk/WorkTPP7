package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.data

import androidx.lifecycle.LiveData
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.ElectricMotor

interface IElectricMotorRepository {

    fun getAllCategoryElectricMotor(name: String): LiveData<List<ElectricMotor>>

    fun addElectricMotor (electricMotor: ElectricMotor)

}

