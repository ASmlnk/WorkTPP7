package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.data

import androidx.lifecycle.LiveData
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.ElectricMotor
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class FirestoreElectricMotorRepository: IElectricMotorRepository {

    val remoteDB = FirebaseFirestore.getInstance()

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

    override fun addSchemaState(electricMotor: ElectricMotor, nameCategory: String, isChecked:Boolean) {
        val data = HashMap<String, Any>()
        data["schemaState"] = isChecked
        remoteDB.collection(nameCategory).document(electricMotor.id)
            .set(data, SetOptions.merge())
    }

}