package com.asmlnk.android.asmlnk.worktpp_7.InspectionSchedule

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.asmlnk.android.asmlnk.worktpp_7.dataFirestore.FirestoreRepository
import com.google.android.gms.tasks.Task
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.ktx.toObject
import java.util.*

class InspectionScheduleViewModel: ViewModel() {

    val firestoreRepository = FirestoreRepository()
    val inspectionScheduleLiveDataN = MutableLiveData<List<Inspection>>()
    val inspectionScheduleLiveDataD = MutableLiveData<List<Inspection>>()

    fun getInspectorSchedule ( workingShift: String, executor: String ) {

        val calendar = getCalendar(workingShift)

        val year = calendar.get ( Calendar.YEAR )
        val month = calendar.get ( Calendar.MONTH )
        val dayMonth = calendar.get ( Calendar.DAY_OF_MONTH )
        val dayWeek = calendar.get ( Calendar.DAY_OF_WEEK )
        val week = calendar.get ( Calendar.DAY_OF_WEEK_IN_MONTH )
        val maxDayMonth = calendar.getActualMaximum ( Calendar.DAY_OF_MONTH )

        val todayData = GregorianCalendar ( year,month,dayMonth )
        val firstData = GregorianCalendar(2023,0,13)
        val intervalCalculation = ( ( todayData.timeInMillis - firstData.timeInMillis ) / ( 1000*60*60*24 ) ) % 10

        var monthData = InSc.ALL.get
        var dayMonthData = InSc.ALL.get
        var dayWeekData = InSc.ALL.get
        var weekData = InSc.ALL.get

        when (month) {
            0 -> monthData = InSc.JANUARY.get
            1 -> monthData = InSc.FEBRUARY.get
            2 -> monthData = InSc.MARCH.get
            3 -> monthData = InSc.APRIL.get
            4 -> monthData = InSc.MAY.get
            5 -> monthData = InSc.JUNE.get
            6 -> monthData = InSc.JULY.get
            7 -> monthData = InSc.AUGUST.get
            8 -> monthData = InSc.SEPTEMBER.get
            9 -> monthData = InSc.OCTOBER.get
            10 -> monthData = InSc.NOVEMBER.get
            11 -> monthData = InSc.DECEMBER.get
        }

        when (dayMonth) {
            1 -> dayMonthData = "1"
            2 -> dayMonthData = "2"
            15 -> dayMonthData = "15"
            19 -> dayMonthData = "19"
            20 -> dayMonthData = "20"
        }

        when (dayWeek) {
            1 -> dayWeekData = InSc.SUNDAY.get
            2 -> dayWeekData = InSc.MONDAY.get
            3 -> dayWeekData = InSc.TUESDAY.get
            4 -> dayWeekData = InSc.WEDNESDAY.get
            5 -> dayWeekData = InSc.THURSDAY.get
            6 -> dayWeekData = InSc.FRIDAY.get
            7 -> dayWeekData = InSc.SATURDAY.get
        }

        when (week) {
            1 -> weekData = InSc.FIRST.get
            2 -> weekData = InSc.SECOND.get
            3 -> weekData = InSc.THIRD.get
        }

        val lastWeek = maxDayMonth - dayMonth
        if (lastWeek < 7 ) weekData = InSc.LAST.get

        val listMonth = listOf(0,2,3,5,6,8,9,11)
        val listDayMonth = listOf(1,2,15,19,20)
        val listDayWeek = listOf(1,2,3,4,5,6,7)
        val listWeek = listOf ( InSc.FIRST.get, InSc.SECOND.get, InSc.THIRD.get, InSc.LAST.get )

        if ( intervalCalculation.toInt() == 0 ) {
            getDataAll ( workingShift, executor, listOf (0 ,10 ) )
        } else {
            getDataAll( workingShift, executor, listOf ( 0 ) )
        }

        if (month in listMonth && dayMonth in listDayMonth) {
            getDataMonthDayMonth ( workingShift, executor, monthData, dayMonthData )
        }
        if (dayWeek in listDayWeek && weekData in listWeek) {
            getDataWeek ( workingShift, executor, listOf( InSc.ALL.get, monthData ), weekData, dayWeekData )
        }
        if (dayMonth in listDayMonth) {
            getDataDayMonth ( workingShift, executor, dayMonthData )
        }
        if (dayWeek in listDayWeek) {
            getDataDayWeek ( workingShift, executor, dayWeekData )
        }
    }

        fun getCalendar(workingShift: String): Calendar {

            val calendar = GregorianCalendar.getInstance(TimeZone.getTimeZone("GMT+3")).apply{
                firstDayOfWeek = 2
            }
            val hour = calendar.get( Calendar.HOUR_OF_DAY )
            if (workingShift == InSc.NIGHT.get && hour in 0..7) calendar.add(Calendar.DAY_OF_WEEK, -1)

            return calendar
        }

        fun getDataAll ( workingShift: String, executor: String, list: List<Int> ) {

            val getData = firestoreRepository
                .getInspectionScheduleDataAll (workingShift, executor, list)
                .get()

            addLiveData(getData, executor)
        }

        fun getDataWeek ( workingShift: String, executor: String, list: List<String>, week: String, dayOfTheWeek: String ) {

            val getData = firestoreRepository
                .getInspectionScheduleDataWeek ( workingShift, executor, list, week, dayOfTheWeek )
                .get()

            addLiveData(getData, executor)
        }

        fun getDataDayMonth (workingShift: String, executor: String, dayMonth: String) {

            val getData = firestoreRepository
                .getInspectionScheduleDataDayMonth( workingShift, executor, dayMonth )
                .get()

            addLiveData(getData, executor)
        }

        fun getDataDayWeek (workingShift: String, executor: String, dayOfTheWeek: String) {

            val getData = firestoreRepository
                .getInspectionScheduleDataDayWeek(workingShift,executor,dayOfTheWeek)
                .get()

            addLiveData(getData, executor)
        }

        fun getDataMonthDayMonth (workingShift: String, executor: String, month: String, dayMonth: String ) {

            val getData = firestoreRepository
                .getInspectionScheduleDataMonthDayMonth(workingShift, executor, month, dayMonth)
                .get()

            addLiveData(getData, executor)
    }

    fun addLiveData(getData: Task<QuerySnapshot>, executor: String) {

        getData.addOnSuccessListener { doc ->
            val listCategory: MutableList<Inspection> = mutableListOf()
            for (it in doc) {
                listCategory.add(it.toObject<Inspection>().apply { id = it.id })
            }

            when (executor) {
                InSc.NSE.get -> inspectionScheduleLiveDataN.value = listCategory
                InSc.DEM.get -> inspectionScheduleLiveDataD.value = listCategory
            }
        }
    }
}