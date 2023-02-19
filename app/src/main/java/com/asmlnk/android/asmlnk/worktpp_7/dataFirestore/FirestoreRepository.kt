package com.asmlnk.android.asmlnk.worktpp_7.dataFirestore

import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.ElectricMotor
import com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule.InSc
import com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule.Inspection
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.Electrolysis
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.*

class FirestoreRepository: IFirestoreRepository {

    val remoteDB = FirebaseFirestore.getInstance()

    override fun getInspectionScheduleDataAll(
        workingShift: String,
        executor: String,
        list: List<Int>
    ): Query {

        return  remoteDB
            .collection ( InSc.INSPECTION_SCHEDULE.get )
            .whereEqualTo ( InSc.WORKING_SHIFT.get, workingShift )
            .whereEqualTo ( InSc.EXECUTOR.get, executor )
            .whereEqualTo ( InSc.MONTH.get, InSc.ALL.get )
            .whereEqualTo ( InSc.DAY_OF_THE_WEEK.get, InSc.ALL.get )
            .whereEqualTo ( InSc.WEEK.get, InSc.ALL.get )
            .whereEqualTo ( InSc.DAY_MONTH.get, InSc.ALL.get )
            .whereIn( InSc.INTERVAL.get, list )

    }

    override fun getInspectionScheduleDataWeek(
        workingShift: String,
        executor: String,
        list: List<String>,
        week: String,
        dayOfTheWeek: String
    ): Query {

        return remoteDB
            .collection ( InSc.INSPECTION_SCHEDULE.get )
            .whereEqualTo ( InSc.WORKING_SHIFT.get, workingShift )
            .whereEqualTo ( InSc.EXECUTOR.get, executor )
            .whereIn ( InSc.MONTH.get, list )
            .whereEqualTo ( InSc.DAY_OF_THE_WEEK.get, dayOfTheWeek )
            .whereEqualTo ( InSc.WEEK.get, week )
            .whereEqualTo ( InSc.DAY_MONTH.get, InSc.ALL.get )
            .whereEqualTo ( InSc.INTERVAL.get, 0 )

    }

    override fun getInspectionScheduleDataDayMonth(
        workingShift: String,
        executor: String,
        dayMonth: String
    ): Query {

        return remoteDB
            .collection ( InSc.INSPECTION_SCHEDULE.get )
            .whereEqualTo ( InSc.WORKING_SHIFT.get, workingShift )
            .whereEqualTo ( InSc.EXECUTOR.get, executor )
            .whereEqualTo ( InSc.MONTH.get, InSc.ALL.get )
            .whereEqualTo ( InSc.DAY_OF_THE_WEEK.get, InSc.ALL.get )
            .whereEqualTo ( InSc.WEEK.get, InSc.ALL.get )
            .whereEqualTo ( InSc.DAY_MONTH.get, dayMonth )
            .whereEqualTo ( InSc.INTERVAL.get, 0 )
    }

    override fun getInspectionScheduleDataDayWeek(
        workingShift: String,
        executor: String,
        dayOfTheWeek: String
    ): Query {

        return remoteDB
            .collection ( InSc.INSPECTION_SCHEDULE.get )
            .whereEqualTo ( InSc.WORKING_SHIFT.get, workingShift )
            .whereEqualTo ( InSc.EXECUTOR.get, executor )
            .whereEqualTo ( InSc.MONTH.get, InSc.ALL.get )
            .whereEqualTo ( InSc.DAY_OF_THE_WEEK.get, dayOfTheWeek )
            .whereEqualTo ( InSc.WEEK.get, InSc.ALL.get )
            .whereEqualTo ( InSc.DAY_MONTH.get, InSc.ALL.get )
            .whereEqualTo ( InSc.INTERVAL.get, 0 )
    }

    override fun getInspectionScheduleDataMonthDayMonth(
        workingShift: String,
        executor: String,
        month: String,
        dayMonth: String
    ): Query {

        return remoteDB
            .collection ( InSc.INSPECTION_SCHEDULE.get )
            .whereEqualTo ( InSc.WORKING_SHIFT.get, workingShift )
            .whereEqualTo ( InSc.EXECUTOR.get, executor )
            .whereEqualTo ( InSc.MONTH.get, month )
            .whereEqualTo ( InSc.DAY_OF_THE_WEEK.get, InSc.ALL.get )
            .whereEqualTo ( InSc.WEEK.get, InSc.ALL.get )
            .whereEqualTo ( InSc.DAY_MONTH.get, dayMonth )
            .whereEqualTo ( InSc.INTERVAL.get,0 )
    }

    override fun getAllCategoryElectricMotor ( nameCategory: String ): CollectionReference {
        return remoteDB.collection(nameCategory)
    }

    override fun getElectrolysis(): Task<DocumentSnapshot> {
        return remoteDB.collection("Электролизерная").document("Ресивера").get()
    }

    override fun addElectrolysis(electrolysis: Electrolysis) {
        val electrolysisData = HashMap<String, Any>()

        electrolysisData["h2_1"] = electrolysis.h2_1
        electrolysisData["h2_2"] = electrolysis.h2_2
        electrolysisData["h2_3"] = electrolysis.h2_3
        electrolysisData["h2_4"] = electrolysis.h2_4
        electrolysisData["h2_5"] = electrolysis.h2_5
        electrolysisData["h2_6"] = electrolysis.h2_6
        electrolysisData["n2_1"] = electrolysis.n2_1
        electrolysisData["n2_2"] = electrolysis.n2_2
        electrolysisData["n2_3"] = electrolysis.n2_3
        electrolysisData["electrolysis"] = electrolysis.electrolysis

        remoteDB.collection("Электролизерная")
            .document("Ресивера").set(electrolysisData, SetOptions.merge())
    }


    override fun addElectricMotor(electricMotor: ElectricMotor) {
        val electricMotorData = HashMap<String, Any>()

        electricMotorData["category"] = electricMotor.category
        electricMotorData["characteristics"] = electricMotor.characteristics
        electricMotorData["keyCheckBox"] = electricMotor.keyCheckBox
        electricMotorData["name"] = electricMotor.name
        electricMotorData["schemaState"] = electricMotor.schemaState

        remoteDB.collection("Остальное")
            .add(electricMotorData)
    }

    override fun addSchemaState(electricMotor: ElectricMotor, nameCategory: String, isChecked:Boolean) {
        val data = HashMap<String, Any>()
        data["schemaState"] = isChecked
        remoteDB.collection(nameCategory).document(electricMotor.id)
            .set(data, SetOptions.merge())
    }

    override fun addInspection(inspection: Inspection) {
        val inspectionData = HashMap<String, Any>()

        inspectionData["workingShift"] = inspection.workingShift
        inspectionData["executor"] = inspection.executor
        inspectionData["month"] = inspection.month
        inspectionData["dayMonth"] = inspection.dayMonth
        inspectionData["dayOfTheWeek"] = inspection.dayOfTheWeek
        inspectionData["week"] = inspection.week
        inspectionData["interval"] = inspection.interval
        inspectionData["content"] = inspection.content
        inspectionData["timeSpending"] = inspection.timeSpending

        remoteDB.collection("ГрафикОсмотра")
            .add(inspectionData)
    }
}