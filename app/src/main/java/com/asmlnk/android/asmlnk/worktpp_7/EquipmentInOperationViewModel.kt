package com.asmlnk.android.asmlnk.worktpp_7

import androidx.lifecycle.ViewModel

class EquipmentInOperationViewModel: ViewModel() {

    val d1a = ElectricMotor("Д-1А")
    val d1b = ElectricMotor("Д-1Б")
    val dv1a = ElectricMotor("ДВ-1А")
    val dv1b = ElectricMotor("ДВ-1Б")
    val vgdn1 = ElectricMotor("ВГДН-1")
    val listBoilerUnit1 = listOf(d1a, d1b, dv1a, dv1b, vgdn1)

    val d6a = ElectricMotor("Д-7А")
    val d6b = ElectricMotor("Д-7Б")
    val dv6a1 = ElectricMotor("ДВ-7А Iск")
    val dv6a2 = ElectricMotor("ДВ-7А IIск")
    val dv6b1 = ElectricMotor("ДВ-7Б Iск")
    val dv6b2 = ElectricMotor("ДВ-7Б IIск")
    val vgdn6 = ElectricMotor("ВГДН-7")
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

    val boilerUnit1 = EquipmentCategory("К/А-1", listBoilerUnit1)
    val boilerUnit6 = EquipmentCategory("К/А-6", listBoilerUnit6)
    val boilerUnit7 = EquipmentCategory("К/А-7", listBoilerUnit7)
    val boilerUnit8 = EquipmentCategory("К/А-8", listBoilerUnit8)
    val boilerUnit9 = EquipmentCategory("К/А-9", listBoilerUnit9)






    inner class  EquipmentCategory(val name: String, val listElectricMotor:  List<ElectricMotor>) {

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