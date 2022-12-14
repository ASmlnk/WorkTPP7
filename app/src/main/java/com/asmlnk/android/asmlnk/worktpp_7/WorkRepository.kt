package com.asmlnk.android.asmlnk.worktpp_7

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.Room
import com.asmlnk.android.asmlnk.worktpp_7.database.DefectDatabase
import java.lang.IllegalStateException
import java.util.*

private const val DATABASE_NAME = "defect-database"

class WorkRepository private constructor(context: Context) {

    private val database : DefectDatabase = Room.databaseBuilder(
        context.applicationContext,
        DefectDatabase::class.java,
        DATABASE_NAME
    ).build()

    private val defectDao = database.defectDao()

    fun getDefects(): LiveData<List<Defect>> = defectDao.getDefects()
    fun getDefect(id: UUID): LiveData<Defect?> = defectDao.getDefect(id)

    companion object {
        private var INSTANCE: WorkRepository? = null

        fun initialize(context: Context) {
            if (INSTANCE == null) {
                INSTANCE = WorkRepository(context)
            }
        }

        fun get(): WorkRepository {
            return INSTANCE ?: throw IllegalStateException("CrimeRepository must be initialized")
        }
    }
}