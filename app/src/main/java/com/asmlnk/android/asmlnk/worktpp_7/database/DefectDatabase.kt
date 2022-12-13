package com.asmlnk.android.asmlnk.worktpp_7.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asmlnk.android.asmlnk.worktpp_7.Defect

@Database(entities = [Defect::class], version = 1)
@TypeConverters(DefectTypeConverter::class)

abstract class DefectDatabase: RoomDatabase()  {
    abstract fun defectDao(): DefectDao
}