package com.asmlnk.android.asmlnk.worktpp_7.dataFirestore

import com.asmlnk.android.asmlnk.worktpp_7.EquipmentWork.ElectricMotor
import com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule.Inspection
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.Query

interface IFirestoreRepository {

    fun getInspectionScheduleDataAll ( workingShift: String,
                                       executor: String,
                                       list: List<Int> ): Query

    fun getInspectionScheduleDataWeek ( workingShift: String,
                                        executor: String,
                                        list: List<String>,
                                        week: String,
                                        dayOfTheWeek: String): Query

    fun getInspectionScheduleDataDayMonth ( workingShift: String,
                                            executor: String,
                                            dayMonth: String ): Query

    fun getInspectionScheduleDataDayWeek ( workingShift: String,
                                           executor: String,
                                           dayOfTheWeek: String ): Query

    fun getInspectionScheduleDataMonthDayMonth ( workingShift: String,
                                                 executor: String,
                                                 month: String,
                                                 dayMonth: String ): Query

    fun getAllCategoryElectricMotor ( name: String ): CollectionReference

    fun addElectricMotor ( electricMotor: ElectricMotor )

    fun addSchemaState ( electricMotor: ElectricMotor, nameCategory: String, isChecked:Boolean )

    fun addInspection ( inspection: Inspection )

}

