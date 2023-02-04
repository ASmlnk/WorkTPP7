package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asmlnk.android.asmlnk.worktpp_7.dataFirestore.FirestoreRepository
import com.google.firebase.firestore.ktx.toObject

class EquipmentInOperationViewModel: ViewModel() {

    val firestoreElectricMotorRepository = FirestoreRepository()

    val boilerUnitLiveData = MutableLiveData<List<EquipmentCategory>>()
    val turbogeneratorLiveData = MutableLiveData<List<EquipmentCategory>>()
    val otherLiveData = MutableLiveData<List<EquipmentCategory>>()

    fun getEquipmentCategory (nameCategory: String) {
        val data = firestoreElectricMotorRepository
            .getAllCategoryElectricMotor(nameCategory).get()

        data.addOnSuccessListener { doc ->
           // val listCategory: List<ElectricMotor> = doc.toObjects(ElectricMotor::class.java)

            val listCategory: MutableList<ElectricMotor> = mutableListOf()
            for (it in doc) {
                listCategory.add(it.toObject<ElectricMotor>().apply { id = it.id })
            }

            val listEquipmentCategory: MutableList<EquipmentCategory> = mutableListOf()
            listCategory.groupBy { it.category }.forEach {
                listEquipmentCategory.add(EquipmentCategory(it.key, it.value))
            }
            listEquipmentCategory.sortBy { it.name }

            when (nameCategory) {
                EM.CAT_BOILER_UNIT.кеуElectricMotor -> boilerUnitLiveData.value = listEquipmentCategory
                EM.CAT_TURBOGENERATOR.кеуElectricMotor -> turbogeneratorLiveData.value = listEquipmentCategory
                EM.CAT_OTHER.кеуElectricMotor -> otherLiveData.value = listEquipmentCategory
            }
        }
    }

    inner class  EquipmentCategory(val name: String,
                                   val listElectricMotor:  List<ElectricMotor> = emptyList()) {
    }
}