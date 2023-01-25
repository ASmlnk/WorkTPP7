package com.asmlnk.android.asmlnk.worktpp_7.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asmlnk.android.asmlnk.worktpp_7.Defect.Defect
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.Compressor

@Database(version = 1, entities = [Defect::class, Compressor::class])
@TypeConverters(DefectTypeConverter::class)

abstract class DefectDatabase: RoomDatabase()  {

    abstract fun defectDao(): DefectDao
    abstract fun compressorDao(): CompressorDAO

}