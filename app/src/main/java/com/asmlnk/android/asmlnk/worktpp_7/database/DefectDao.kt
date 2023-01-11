package com.asmlnk.android.asmlnk.worktpp_7.database

import androidx.lifecycle.LiveData
import androidx.room.*
import com.asmlnk.android.asmlnk.worktpp_7.Defect.Defect
import java.util.*

@Dao
interface DefectDao {
    @Query("SELECT * FROM defect")
    fun getDefects(): LiveData<List<Defect>>

    @Query("SELECT * FROM defect WHERE id=( :id)")
    fun  getDefect(id: UUID): LiveData<Defect?>

    @Update
    fun updateDefect(defect: Defect)

    @Insert
    fun addDefect(defect: Defect)

    @Delete
    fun deleteDefect(defect: Defect)
}