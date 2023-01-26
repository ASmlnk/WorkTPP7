package com.asmlnk.android.asmlnk.worktpp_7.Defect

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.asmlnk.android.asmlnk.worktpp_7.Defect.Defect
import com.asmlnk.android.asmlnk.worktpp_7.WorkingShift.Compressor
import com.asmlnk.android.asmlnk.worktpp_7.database.DefectDatabase
import java.io.File
import java.lang.IllegalStateException
import java.util.*
import java.util.concurrent.Executors

private const val DATABASE_NAME = "defect-database"

class DefectRepository private constructor(context: Context) {

    private val database : DefectDatabase = Room.databaseBuilder(
        context.applicationContext,
        DefectDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val defectDao = database.defectDao()
    private val compressorDao = database.compressorDao()
    private val executor = Executors.newSingleThreadExecutor() // фоновый поток в котором будут выполняться нужные функции
    private val filesDir = context.applicationContext.filesDir

    fun getDefects(): LiveData<List<Defect>> = defectDao.getDefects()
    fun getDefect(id: UUID): LiveData<Defect?> = defectDao.getDefect(id)



    fun updateDefect(defect: Defect) {
        executor.execute {
            defectDao.updateDefect(defect)  //функцию которая будет выполняться в фоновом потоке
        }
    }

    fun addDefect(defect: Defect) {
        executor.execute {
            defectDao.addDefect(defect)   //функцию которая будет выполняться в фоновом потоке
        }
    }

    fun deleteDefect(defect: Defect) {
        executor.execute {
            defectDao.deleteDefect(defect)
        }
    }

    fun getPhotoFile(defect: Defect): File = File(filesDir, defect.photoFileName)

    fun getCompressors(): LiveData<List<Compressor>> = compressorDao.getCompressors()

    fun addCompressor(compressor: Compressor) {
        executor.execute {
            compressorDao.addCompressor(compressor)
        }
    }

    fun deleteCompressor(compressor: Compressor) {
        executor.execute {
            compressorDao.deleteCompressor(compressor)
        }
    }

    companion object {
        private var INSTANCE: DefectRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = DefectRepository(context)
            }
        }

        fun get(): DefectRepository {
            return INSTANCE ?: throw IllegalStateException("DefectRepository must be initialized")
        }
    }
}