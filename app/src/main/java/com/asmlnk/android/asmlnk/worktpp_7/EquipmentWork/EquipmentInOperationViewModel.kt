package com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asmlnk.android.asmlnk.worktpp_7.ELECTRICMOTOR
import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.data.FirestoreElectricMotorRepository
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.firestore.ktx.toObject
import com.google.firebase.ktx.Firebase

private val CAT_BOILER_UNIT = ELECTRICMOTOR.CAT_BOILER_UNIT
private val CAT_TURBOGENERATOR = ELECTRICMOTOR.CAT_TURBOGENERATOR
private val CAT_OTHER = ELECTRICMOTOR.CAT_OTHER

class EquipmentInOperationViewModel: ViewModel() {

    val firestoreElectricMotorRepository = FirestoreElectricMotorRepository()

     val boilerUnitLiveData = MutableLiveData<List<EquipmentCategory>>()
    private val turbogeneratorLiveData = MutableLiveData<List<EquipmentCategory>>()
    private val otherLiveData = MutableLiveData<List<EquipmentCategory>>()


    fun getEquipmentCategory (nameCategory: String) {
       /* val data = firestoreElectricMotorRepository
            .getAllCategoryElectricMotor(nameCategory).get()*/

        val data = FirebaseFirestore.getInstance()

        data.collection("Котлоагрегаты").get()

        .addOnSuccessListener { doc ->
            val listCategory: List<ElectricMotor> = doc.toObjects(ElectricMotor::class.java)

            /*val listCategory: MutableList<ElectricMotor> = mutableListOf()
            for (it in doc) {
                listCategory.add(it.toObject<ElectricMotor>().apply { id = it.id })
            }*/

            val ad = listCategory.filter { it.category == "К/А-6" }
            val bd = listCategory.filter { it.category == "К/А-7" }
            val df = listCategory.filter { it.category == "К/А-8" }
            val fy = listCategory.filter { it.category == "К/А-9" }




            val ka6 = EquipmentCategory("К/А-6", listElectricMotor = ad)
            val ka7 = EquipmentCategory("К/А-7", listElectricMotor = bd)
            val ka8 = EquipmentCategory("К/А-8", listElectricMotor = df)
            val ka9 = EquipmentCategory("К/А-9", listElectricMotor = fy)

            val listEquipmentCategory= listOf(ka6,ka7,ka8,ka9)

           /* listCategory.groupBy { it.category }.forEach {
                listEquipmentCategory.add(EquipmentCategory(it.key, it.value))
            }*/
            boilerUnitLiveData.value = listEquipmentCategory
        }
    }




    val d1a = ElectricMotor("Д-1А")
    val d1b = ElectricMotor("Д-1Б")
    val dv1a = ElectricMotor("ДВ-1А")
    val dv1b = ElectricMotor("ДВ-1Б")
    val vgdn1 = ElectricMotor("ВГДН-1")
    val listBoilerUnit1 = listOf(d1a, d1b, dv1a, dv1b, vgdn1)

    val d6a = ElectricMotor("Д-6А")
    val d6b = ElectricMotor("Д-6Б")
    val dv6a1 = ElectricMotor("ДВ-6А Iск")
    val dv6a2 = ElectricMotor("ДВ-6А IIск")
    val dv6b1 = ElectricMotor("ДВ-6Б Iск")
    val dv6b2 = ElectricMotor("ДВ-6Б IIск")
    val vgdn6 = ElectricMotor("ВГДН-6")
    val listBoilerUnit6 = listOf(d6a, d6b, dv6a1, dv6a2, dv6b1, dv6b2, vgdn6)

    val d7a = ElectricMotor("Д-7А")
    val d7b = ElectricMotor("Д-7Б")
    val dv7a1 = ElectricMotor("ДВ-7А Iск")
    val dv7a2 = ElectricMotor("ДВ-7А IIск")
    val dv7b1 = ElectricMotor("ДВ-7Б Iск")
    val dv7b2 = ElectricMotor("ДВ-7Б IIск")
    val vgdn7 = ElectricMotor("ВГДН-7")
    val listBoilerUnit7 = listOf(d7a, d7b, dv7a1, dv7a2, dv7b1, dv7b2, vgdn7)

    val d8a = ElectricMotor("Д-8А")
    val d8b = ElectricMotor("Д-8Б")
    val dv8a1 = ElectricMotor("ДВ-8А Iск")
    val dv8a2 = ElectricMotor("ДВ-8А IIск")
    val dv8b1 = ElectricMotor("ДВ-8Б Iск")
    val dv8b2 = ElectricMotor("ДВ-8Б IIск")
    val vgdn8 = ElectricMotor("ВГДН-8")
    val listBoilerUnit8 = listOf(d8a, d8b, dv8a1, dv8a2, dv8b1, dv8b2, vgdn8)

    val d9a = ElectricMotor("Д-9А")
    val d9b = ElectricMotor("Д-9Б")
    val dv9a1 = ElectricMotor("ДВ-9А Iск")
    val dv9a2 = ElectricMotor("ДВ-9А IIск")
    val dv9b1 = ElectricMotor("ДВ-9Б Iск")
    val dv9b2 = ElectricMotor("ДВ-9Б IIск")
    val vgdn9 = ElectricMotor("ВГДН-9")
    val listBoilerUnit9 = listOf(d9a, d9b, dv9a1, dv9a2, dv9b1, dv9b2, vgdn9)

    val mapBoilerUnit = listOf(listBoilerUnit6,listBoilerUnit7,  listBoilerUnit8,  listBoilerUnit9)

    val boilerUnit1 = EquipmentCategory("К/А-1", listBoilerUnit1)
    val boilerUnit6 = EquipmentCategory("К/А-6", listBoilerUnit6)
    val boilerUnit7 = EquipmentCategory("К/А-7", listBoilerUnit7)
    val boilerUnit8 = EquipmentCategory("К/А-8", listBoilerUnit8)
    val boilerUnit9 = EquipmentCategory("К/А-9", listBoilerUnit9)

    val listBoilerUnit = listOf(boilerUnit6,boilerUnit7,boilerUnit8,boilerUnit9)

    val gon1a = ElectricMotor("ГОН-1А")
    val gon1b = ElectricMotor("ГОН-1Б")
    val mnsPost1 = ElectricMotor("МНС-1=")
    val mns1 = ElectricMotor("МНС-1")
    val mnuPost1 = ElectricMotor("МНУ-1=")
    val mnu1 = ElectricMotor("МНУ-1")
    val vpu1 = ElectricMotor("ВПУ-1")
    val pmn1 = ElectricMotor("ПМН-1")
    val knb2a = ElectricMotor("КНБ-2А")
    val knb2b = ElectricMotor("КНБ-2Б")


    val gon3a = ElectricMotor("ГОН-3А")
    val gon3b = ElectricMotor("ГОН-3Б")
    val mnsPost3 = ElectricMotor("МНС-3=")
    val mns3 = ElectricMotor("МНС-3")
    val mnuPost3 = ElectricMotor("МНУ-3=")
    val mnu3a = ElectricMotor("МНУ-3А")
    val mnu3b = ElectricMotor("МНУ-3Б")
    val vpu3 = ElectricMotor("ВПУ-3")
    val pmn3 = ElectricMotor("ПМН-3")
    val knb3a = ElectricMotor("КНБ-3А")
    val knb3b = ElectricMotor("КНБ-3Б")
    val kn3a = ElectricMotor("КН-3А")
    val kn3b = ElectricMotor("КН-3Б")




    val gon4a = ElectricMotor("ГОН-4А")
    val gon4b = ElectricMotor("ГОН-4Б")
    val mnsPost4 = ElectricMotor("МНС-4=")
    val mns4 = ElectricMotor("МНС-4")
    val mnuPost4 = ElectricMotor("МНУ-4=")
    val mnu4a = ElectricMotor("МНУ-4")
    val vpu4 = ElectricMotor("ВПУ-4")
    val pmn4 = ElectricMotor("ПМН-4")
    val knb4a = ElectricMotor("КНБ-4А")
    val knb4b = ElectricMotor("КНБ-4Б")
    val kn4a = ElectricMotor("КН-4А")
    val kn4b = ElectricMotor("КН-4Б")
    val sln4a = ElectricMotor("СлН-4А")
    val sln4b = ElectricMotor("СлН-4Б")

    val gon5a = ElectricMotor("ГОН-5А")
    val gon5b = ElectricMotor("ГОН-5Б")
    val mnsPost5 = ElectricMotor("МНС-5=")
    val mns5 = ElectricMotor("МНС-5")
    val mnuPost5 = ElectricMotor("МНУ-5=")
    val mnu5a = ElectricMotor("МНУ-5А")
    val mnu5b = ElectricMotor("МНУ-5Б")
    val vpu5 = ElectricMotor("ВПУ-5")
    val pmn5 = ElectricMotor("ПМН-5")
    val knb5a = ElectricMotor("КНБ-5А")
    val knb5v = ElectricMotor("КНБ-5В")
    val knb5g = ElectricMotor("КНБ-5Г")
    val kn5a = ElectricMotor("КН-1")
    val kn5b = ElectricMotor("КН-2")
    val kn5c = ElectricMotor("КН-3")
    val sln5a = ElectricMotor("СлН-5А")
    val sln5b = ElectricMotor("СлН-5Б")



    val gon6a = ElectricMotor("ГОН-6А")
    val gon6b = ElectricMotor("ГОН-6Б")
    val mnsPost6 = ElectricMotor("МНС-6=")
    val mns6 = ElectricMotor("МНС-6")
    val mnuPost6 = ElectricMotor("МНУ-6=")
    val mnu6a = ElectricMotor("МНУ-6А")
    val mnu6b = ElectricMotor("МНУ-6Б")
    val vpu6 = ElectricMotor("ВПУ-6")
    val pmn6 = ElectricMotor("ПМН-6")
    val ntv3 = ElectricMotor("НТВ-3")
    val ntv4 = ElectricMotor("НТВ-4")





    val mapTurbogenerator1 = mapOf(
        "ГОН-А" to gon1a,
        "ГОН-Б" to gon1b,
        "МНС=" to mnsPost1,
        "МНС" to mns1,
        "МНУ=" to mnuPost1,
        "МНУ-А" to mnu1,
        "ВПУ" to vpu1,
        "ПМН" to pmn1,
        "КНБ-А" to knb2a,
        "КНБ-Б" to knb2b)

    val mapTurbogenerator3 = mapOf(
        "ГОН-А" to gon3a,
        "ГОН-Б" to gon3b,
        "МНС=" to mnsPost3,
        "МНС" to mns3,
        "МНУ=" to mnuPost3,
        "МНУ-А" to mnu3a,
        "МНУ-Б" to mnu3b,
        "ВПУ" to vpu3,
        "ПМН" to pmn3,
        "КНБ-А" to knb3a,
        "КНБ-Б" to knb3b,
        "КН-А" to kn3a,
        "КН-Б" to kn3b)

    val mapTurbogenerator4 = mapOf(
        "ГОН-А" to gon4a,
        "ГОН-Б" to gon4b,
        "МНС=" to mnsPost4,
        "МНС" to mns4,
        "МНУ=" to mnuPost4,
        "МНУ-А" to mnu4a,
        "ВПУ" to vpu4,
        "ПМН" to pmn4,
        "КНБ-А" to knb4a,
        "КНБ-Б" to knb4b,
        "КН-А" to kn4a,
        "КН-Б" to kn4b,
        "СлН-А" to sln4a,
        "СлН-Б" to sln4b)

    val mapTurbogenerator5 = mapOf(
        "ГОН-А" to gon5a,
        "ГОН-Б" to gon5b,
        "МНС=" to mnsPost5,
        "МНС" to mns5,
        "МНУ=" to mnuPost5,
        "МНУ-А" to mnu5a,
        "МНУ-Б" to mnu5b,
        "ВПУ" to vpu5,
        "ПМН" to pmn5,
        "КНБ-А" to knb5a,
        "КНБ-Б" to knb5v,
        "КНБ-В" to knb5g,
        "КН-А" to kn5a,
        "КН-Б" to kn5b,
        "КН-В" to kn5c,
        "СлН-А" to sln5a,
        "СлН-Б" to sln5b)

    val mapTurbogenerator6 = mapOf(
        "ГОН-А" to gon6a,
        "ГОН-Б" to gon6b,
        "МНС=" to mnsPost6,
        "МНС" to mns6,
        "МНУ=" to mnuPost6,
        "МНУ-А" to mnu6a,
        "МНУ-Б" to mnu6b,
        "ВПУ" to vpu6,
        "ПМН" to pmn6,
        "СлН-А" to ntv3,
        "СлН-Б" to ntv4)

    val turbogenerator1 = EquipmentCategory("ТГ-1", mapElectricMotor = mapTurbogenerator1)
    val turbogenerator3 = EquipmentCategory("ТГ-3", mapElectricMotor = mapTurbogenerator3)
    val turbogenerator4 = EquipmentCategory("ТГ-4", mapElectricMotor = mapTurbogenerator4)
    val turbogenerator5 = EquipmentCategory("ТГ-5", mapElectricMotor = mapTurbogenerator5)
    val turbogenerator6 = EquipmentCategory("ТГ-6", mapElectricMotor = mapTurbogenerator6)

    val listTurbogenerator = listOf(turbogenerator1,turbogenerator3,turbogenerator4,turbogenerator5,turbogenerator6)
















    inner class  EquipmentCategory(val name: String,
                                   val listElectricMotor:  List<ElectricMotor> = emptyList(),
                                   val mapElectricMotor: Map<String, ElectricMotor> = emptyMap()) {

        private fun collectAll (listElectricMotor: List<ElectricMotor>) {
            for (item in listElectricMotor) {
                item.schemaState = true
            }
        }

        private fun takeApartAll (listElectricMotor: List<ElectricMotor>) {
            for (item in listElectricMotor) {
                item.schemaState = false
            }
        }

    }



}