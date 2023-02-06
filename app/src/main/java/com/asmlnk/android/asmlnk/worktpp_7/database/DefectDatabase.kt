package com.asmlnk.android.asmlnk.worktpp_7.database

import androidx.room.AutoMigration
import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.asmlnk.android.asmlnk.worktpp_7.Defect.Defect
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.Compressor
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.GeneratorInsulation

@Database(
    version = 2,
    entities = [Defect::class, Compressor::class, GeneratorInsulation::class],
    autoMigrations = [
        AutoMigration(from = 1, to = 2)
    ])

@TypeConverters(DefectTypeConverter::class)

abstract class DefectDatabase: RoomDatabase()  {

    abstract fun defectDao(): DefectDao
    abstract fun compressorDao(): CompressorDAO
    abstract fun generatorInsulationDao(): GeneratorInsulationDao

}

