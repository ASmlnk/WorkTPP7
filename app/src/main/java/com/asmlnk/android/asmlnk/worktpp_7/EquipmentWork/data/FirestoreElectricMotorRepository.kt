package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.data

import androidx.lifecycle.LiveData
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.ElectricMotor
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore

class FirestoreElectricMotorRepository: IElectricMotorRepository {

    private val remoteDB = FirebaseFirestore.getInstance()

    override fun getAllCategoryElectricMotor(nameCategory: String): CollectionReference {
        return remoteDB.collection(nameCategory)
    }

    override fun addElectricMotor(electricMotor: ElectricMotor) {
        val electricMotorData = HashMap<String, Any>()
        electricMotorData["category"] = electricMotor.category
        electricMotorData["characteristics"] = electricMotor.characteristics
        electricMotorData["keyCheckBox"] = electricMotor.keyCheckBox
        electricMotorData["name"] = electricMotor.name
        electricMotorData["schemaState"] = electricMotor.schemaState

        remoteDB.collection("")
            .add(electricMotorData)
    }
}