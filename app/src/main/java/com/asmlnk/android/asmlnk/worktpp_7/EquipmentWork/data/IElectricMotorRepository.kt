package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.data

import androidx.lifecycle.LiveData
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.ElectricMotor
import com.google.firebase.firestore.CollectionReference

interface IElectricMotorRepository {

    fun getAllCategoryElectricMotor(name: String): CollectionReference

    fun addElectricMotor (electricMotor: ElectricMotor)

}

